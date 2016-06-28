package com.hibernate.curso.jpa2.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.hibernate.curso.jpa2.modelo.Aluguel;

public class AluguelDAO implements Serializable{

	@Inject
	private EntityManager entityManager;
	
	public void salvar(Aluguel aluguel){
		entityManager.merge(aluguel);
	}
}
