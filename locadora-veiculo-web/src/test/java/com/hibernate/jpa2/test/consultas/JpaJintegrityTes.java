package com.hibernate.jpa2.test.consultas;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hibernate.jpa2.dao.CarroDAO;
import com.hibernate.jpa2.modelo.Carro;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class JpaJintegrityTes {

	private JIntegrity helper = new JIntegrity();
	
	private CarroDAO carroDao;
	
	@Before
	public void init(){
		helper.cleanAndInsert();
		this.carroDao = new CarroDAO();
		this.carroDao.setEntityManager(JPAHelper.currentEntityManager());
	}
	
	@Test
	public void testaBuscaCarroPorId(){
		Carro carro = carroDao.buscarPeloCodigo(1L);
		assertEquals(1L, carro.getCodigo().longValue());
		assertEquals("AAA-1111", carro.getPlaca());
	}
}
