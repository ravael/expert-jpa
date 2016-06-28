package com.hibernate.curso.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.hibernate.curso.jpa2.dao.MotoristaDAO;
import com.hibernate.curso.jpa2.modelo.Motorista;

public class MotoristaService implements Serializable{

	@Inject
	private MotoristaDAO motoristaDao;
	
	public List<Motorista> buscaTodosMotorista(){
		return motoristaDao.buscaTodos();
	}
}
