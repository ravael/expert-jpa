package com.hibernate.jpa2.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.YesNoType;

import com.hibernate.jpa2.modelo.Aluguel;
import com.hibernate.jpa2.modelo.ModeloCarro;

public class AluguelDAO implements Serializable{

	@Inject
	private EntityManager entityManager;
	
	public void salvar(Aluguel aluguel){
		entityManager.merge(aluguel);
	}

	public List<Aluguel> buscarPorDataDeEntregaEModeloCarro(Date dataEntrega,ModeloCarro modelo) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Aluguel> criteriaQuery = builder.createQuery(Aluguel.class);
		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);
		criteriaQuery.select(aluguel);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(dataEntrega != null){
			
			ParameterExpression<Date> dataEntregaInicial = builder.parameter(Date.class, "dataEntregaInicial");
			ParameterExpression<Date> dataEntregaFinal = builder.parameter(Date.class, "dataEntregaFinal");
			
			predicates.add(builder.between(aluguel.<Date>get("dataEntrega"), dataEntregaInicial, dataEntregaFinal));
			
		}
		if (modelo != null){
			
			ParameterExpression<ModeloCarro> modeloExpression = builder.parameter(ModeloCarro.class, "modelo");
			
			predicates.add(builder.equal(aluguel.get("carro").get("modelo"), modeloExpression));
		}
		
		criteriaQuery.where((javax.persistence.criteria.Predicate[]) predicates.toArray(new Predicate[0]));
		
		TypedQuery<Aluguel> query = entityManager.createQuery(criteriaQuery);
		
		if(dataEntrega != null){
			
			Calendar dataEntregaInicial = Calendar.getInstance();
			dataEntregaInicial.setTime(dataEntrega);
			dataEntregaInicial.set(Calendar.HOUR_OF_DAY,0);
			dataEntregaInicial.set(Calendar.MINUTE, 0);
			dataEntregaInicial.set(Calendar.SECOND, 0);
			
			Calendar dataEntregaFinal = Calendar.getInstance();
			dataEntregaFinal.setTime(dataEntrega);
			dataEntregaFinal.set(Calendar.HOUR_OF_DAY,23);
			dataEntregaFinal.set(Calendar.MINUTE, 59);
			dataEntregaFinal.set(Calendar.SECOND, 59);
			
			query.setParameter("dataEntregaInicial", dataEntregaInicial.getTime());
			query.setParameter("dataEntregaFinal", dataEntregaFinal.getTime());
			
		}
		
		if(modelo != null){
			query.setParameter("modelo", modelo);
		}
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Aluguel> buscarPorDataDeEntregaEModeloCarroCriteria(
			Date dataEntrega, ModeloCarro modelo) {
		
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Aluguel.class);
		
		if (dataEntrega != null){
			criteria.add(Restrictions.between("dataEntrega", getDataInicial(dataEntrega),
					getDataFinal(dataEntrega)));
		}
		
		if (modelo != null){
			criteria.createAlias("carro", "c");
			criteria.add(Restrictions.eq("c.modelo", modelo));
		}
		
		return criteria.list();
	}
	
	private Date getDataInicial(Date dataEntrega){
		Calendar dataEntregaInicial = Calendar.getInstance();
		dataEntregaInicial.setTime(dataEntrega);
		dataEntregaInicial.set(Calendar.HOUR_OF_DAY,0);
		dataEntregaInicial.set(Calendar.MINUTE, 0);
		dataEntregaInicial.set(Calendar.SECOND, 0);
		
		return dataEntregaInicial.getTime();
	}
	
	private Date getDataFinal(Date dataEntrega){
		Calendar dataEntregaFinal = Calendar.getInstance();
		dataEntregaFinal.setTime(dataEntrega);
		dataEntregaFinal.set(Calendar.HOUR_OF_DAY,23);
		dataEntregaFinal.set(Calendar.MINUTE, 59);
		dataEntregaFinal.set(Calendar.SECOND, 59);
		return dataEntregaFinal.getTime();
	}

	public BigDecimal calcularTotalDoMesDe(int mes) {
		
		Session session = entityManager.unwrap(Session.class);
		Criteria  criteria = session.createCriteria(Aluguel.class);
		
		criteria.setProjection(Projections.sum("valorTotal"));
		criteria.add(Restrictions.sqlRestriction("month(dataPedido) = ?", mes, StandardBasicTypes.INTEGER));
		return (BigDecimal) criteria.uniqueResult();
	}
}
