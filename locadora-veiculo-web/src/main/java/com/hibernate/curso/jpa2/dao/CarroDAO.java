package com.hibernate.curso.jpa2.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.hibernate.curso.jpa2.modelo.Carro;

public class CarroDAO {
	
	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Carro> buscaTodos(){
		return entityManager.createQuery("from Carro").getResultList();
	}
	
	public void salvar(Carro carro){
		entityManager.merge(carro);
	}
}
