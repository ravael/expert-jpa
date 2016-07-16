package com.hibernate.jpa2.test.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.jpa2.modelo.Carro;

public class JpaByFilterTest {

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
	public void buscaPelaMarca(){
		
		String fabricante = "Ford";
		
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Carro.class);
		
		criteria.createAlias("modelo", "m")
				.createAlias("m.fabricante", "f")
				.add(Restrictions.ilike("f.nome", fabricante, MatchMode.ANYWHERE));
		
		List<Carro> carros = criteria.list();
		
		for (Carro c : carros) {
			System.err.printf("Modelo: %s Fabricante: %s", c.getModelo().getDescricao(), c.getModelo().getFabricante().getNome());
		}
	}
	
	@After
	public void tearDown(){
		this.entityManager.close();
	}
	
}
