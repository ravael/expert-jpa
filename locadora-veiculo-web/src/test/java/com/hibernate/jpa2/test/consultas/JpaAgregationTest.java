package com.hibernate.jpa2.test.consultas;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.jpa2.modelo.Aluguel;

public class JpaAgregationTest {


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
	public void agregationTotal(){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);
		
		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);
		criteriaQuery.select(builder.sum(aluguel.<BigDecimal>get("valorTotal")));
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteriaQuery);
		
		BigDecimal total = query.getSingleResult();
		
		System.err.println("Valor total dos alugueis: " + total);
	}
	
	
	@After
	public void tearDown(){
		this.entityManager.clear();
	}
}
