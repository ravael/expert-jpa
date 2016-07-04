package com.hibernate.jpa2.test.consultas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.jpa2.modelo.Carro;

public class JpaFetchModeTest {
	
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
	public void buscaCarro(){
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);
		criteria.setFetchMode("acessorios", FetchMode.JOIN);
		
		criteria.add(Restrictions.eq("codigo", 4L));
		
		Carro carro = (Carro) criteria.uniqueResult();
		
		System.err.println(carro.getAcessorios().get(0).getDescricao());
	}
	
	
	@After
	public void tearDown(){
		this.entityManager.close();
	}

}
