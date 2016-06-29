package com.hibernate.jpa2.test.consultas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.jpa2.modelo.Acessorio;
import com.hibernate.jpa2.modelo.Carro;
import com.hibernate.jpa2.modelo.Categoria;
import com.hibernate.jpa2.modelo.ModeloCarro;

public class JpaEntidadeTransientTest {
	
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
	public void testCarroModeloTransient(){
		
		Carro carro = new Carro();
		carro.setCor("Preto");
		carro.setPlaca("XXXX-5050");
		
		ModeloCarro modelo = new ModeloCarro();
		modelo.setCategoria(Categoria.ESPORTIVO);
		modelo.setDescricao("Ford GT");
		carro.setModelo(modelo);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(carro);
		this.entityManager.getTransaction().commit();
		
	}
	
	@Test
	public void testCarroAcessorioTransient(){
		
		Carro carro = new Carro();
		carro.setPlaca("VVVV-3636");
		carro.setCor("Cinza");
		
		Acessorio ar = new Acessorio();
		ar.setDescricao("Ar Condicionado");
		Acessorio mp3 = new Acessorio();
		mp3.setDescricao("Mp3 Player");
		
		List<Acessorio> acessorios = new ArrayList<Acessorio>();
		acessorios.add(ar);
		acessorios.add(mp3);
		carro.setAcessorios(acessorios);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(carro);
		this.entityManager.getTransaction().commit();
	}
	
	@After
	public void tearDown(){
		this.entityManager.close();
	}

}
