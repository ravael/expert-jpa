package com.hibernate.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.hibernate.jpa2.modelo.Motorista;

public class MotoristaDAO implements Serializable{

	@Inject
	private EntityManager entityManager;
	
	public void salvar(Motorista motorista){
		entityManager.merge(motorista);
	}
	
	public Motorista buscarPeloCodigo(Long codigo){
		return entityManager.find(Motorista.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Motorista> buscaTodos() {
		return entityManager.createQuery("from Motorista").getResultList();
	}

	public void excluir(Motorista motorista) {
		motorista = entityManager.find(Motorista.class, motorista.getCodigo());
		entityManager.remove(motorista);
		entityManager.flush();
		
	}
}
