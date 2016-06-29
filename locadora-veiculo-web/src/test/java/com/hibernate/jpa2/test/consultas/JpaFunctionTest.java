package com.hibernate.jpa2.test.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.jpa2.modelo.Carro;

public class JpaFunctionTest {


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
	public void carroFunction(){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Predicate predicate = builder.equal(builder.upper(carro.<String>get("cor")), "preto".toUpperCase());
		
		criteriaQuery.select(carro);
		criteriaQuery.where(predicate);
		
		TypedQuery<Carro> query = entityManager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();
		
		for (Carro car : carros) {
			System.err.println(car.getPlaca() + "-" + car.getCor());
		}
		
	}
	
	
	@After
	public void tearDown(){
		this.entityManager.clear();
	}
}
