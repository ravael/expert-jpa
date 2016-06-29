package com.hibernate.jpa2.test.consultas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.jpa2.modelo.Carro;

public class JpaMetaModelCarroTest {

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
	public void carroMetaModelTest(){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		
		
	}
	
	@After
	public void tearDown(){
		this.entityManager.close();
	}
}
