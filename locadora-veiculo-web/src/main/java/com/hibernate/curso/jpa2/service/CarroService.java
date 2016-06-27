package com.hibernate.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.hibernate.curso.jpa2.dao.CarroDAO;
import com.hibernate.curso.jpa2.modelo.Carro;
import com.hibernate.curso.jpa2.util.jpa.Transactional;

public class CarroService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarroDAO carroDao;
	
	@Transactional
	public void salvar(Carro carro) throws NegocioException{
		carroDao.salvar(carro);
	}
	
}
