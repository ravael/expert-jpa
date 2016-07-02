package com.hibernate.jpa2.test.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.jpa2.modelo.Carro;
import com.hibernate.jpa2.test.model.CarroDTO;

public class JpaTuplasTest {

	

	private static EntityManagerFactory entityManagerFactory;
	
	private EntityManager entityManager;

	@BeforeClass
	public static void init(){
		entityManagerFactory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}
	
	@Before
	public void setup(){
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Test
	public void objetoDeCarro(){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.multiselect(carro.get("modelo").get("descricao"), carro.get("placa"), carro.get("valorDiaria"));
		
		TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
		List<Object[]> resultado = query.getResultList();
		
		for (Object[] valores : resultado) {
			System.err.println(valores[0] + "-" + valores[1] + "-" + valores[2]);
		}
	}
	
	@Test
	public void listaCarro(){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criateriaQuery = builder.createTupleQuery();
		
		Root<Carro> carro = criateriaQuery.from(Carro.class);
		criateriaQuery.multiselect(carro.get("modelo").get("descricao").alias("descricao"), carro.get("placa").alias("placa"), carro.get("valorDiaria").alias("diaria"));
		
		TypedQuery<Tuple> query = entityManager.createQuery(criateriaQuery);
		List<Tuple> resultado = query.getResultList();
		
		for (Tuple valor : resultado) {
			System.err.println(valor.get("descricao") + "-" + valor.get("placa") + "-" + valor.get("diaria"));
		}
	}
	
	
	@Test
	public void listaCarroDTO(){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarroDTO> criteriaQuery = builder.createQuery(CarroDTO.class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(builder.construct(CarroDTO.class, carro.get("modelo").get("descricao"), carro.get("placa"), carro.get("valorDiaria")));
		
		TypedQuery<CarroDTO> query = entityManager.createQuery(criteriaQuery);
		List<CarroDTO> carros = query.getResultList();
		
		for (CarroDTO car : carros) {
			System.err.println(car.getModelo() + "-" + car.getPlaca() + "-" + car.getValor());
		}
	}
	
	@After
	public void tearDown(){
		this.entityManager.clear();
	}
	
	
}
