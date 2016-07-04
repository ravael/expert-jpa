package com.hibernate.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.hibernate.jpa2.modelo.Aluguel;
import com.hibernate.jpa2.modelo.Carro;
import com.hibernate.jpa2.modelo.dto.AluguelCarroDTO;

public class CarroDAO implements Serializable{
	
	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Carro> buscaTodos(){
		return entityManager.createNamedQuery("Carro.buscarTodos").getResultList();
	}
	
	public void salvar(Carro carro){
		entityManager.merge(carro);
	}

	public Carro buscarPeloCodigo(Long codigo) {
		return entityManager.find(Carro.class, codigo);
	}

	public void excluir(Carro carroSelecionado) {
		carroSelecionado = entityManager.find(Carro.class, carroSelecionado.getCodigo());
		entityManager.remove(carroSelecionado);
		entityManager.flush();
		
	}

	public Carro buscarCarroComAcessorios(Long codigo) {
		return (Carro) entityManager.createNamedQuery("Carro.buscarCarroComACessorios")
				.setParameter("codigo", codigo)
				.getSingleResult();
		
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscaComPaginacao(int first, int pageSize) {
		return entityManager.createNamedQuery("Carro.buscarTodos")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Long encontrarQuantidadeDeCarros() {
		return entityManager.createQuery("select count(c) from Carro c", Long.class).getSingleResult();
	}
	
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscaCarrosNuncaAlugados(){
		
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Aluguel.class);
		detachedCriteria.setProjection(Projections.property("carro"));
		detachedCriteria.add(Restrictions.isNotNull("carro"));
		
		criteria.add(Property.forName("codigo").notIn(detachedCriteria));
		
		return criteria.list();
	}

	public List<AluguelCarroDTO> buscarTotalAlugueisPorCarro() {
		
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);
		
		criteria.createAlias("alugueis", "a");
		ProjectionList list = Projections.projectionList().add(Projections.groupProperty("placa").as("placa"))
														.add(Projections.count("a.codigo").as("totalDeAlugueis"));
		criteria.setProjection(list);
		criteria.addOrder(Order.desc("totalDeAlugueis"));
		criteria.setResultTransformer(Transformers.aliasToBean(AluguelCarroDTO.class));
		
		
		return criteria.list();
	}
}

