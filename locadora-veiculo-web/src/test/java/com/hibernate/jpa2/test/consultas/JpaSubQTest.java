package com.hibernate.jpa2.test.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.jpa2.modelo.Aluguel;
import com.hibernate.jpa2.modelo.Carro;

public class JpaSubQTest {
	

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
	
	@SuppressWarnings("unchecked")
	@Test
	public void testaCarroNuncaAlugados(){
		
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);
	
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Aluguel.class);
		detachedCriteria.setProjection(Projections.property("carro"));
		detachedCriteria.add(Restrictions.isNotNull("carro"));
		
		criteria.add(Property.forName("codigo").notIn(detachedCriteria));
		
		List<Carro> carro = criteria.list();
		
		for (Carro c : carro) {
			System.err.println(c.getPlaca());
		}
		
	}
	
	@After
	public void tearDown(){
		this.entityManager.close();
	}
}
