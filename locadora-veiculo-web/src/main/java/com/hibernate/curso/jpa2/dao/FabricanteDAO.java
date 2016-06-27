package com.hibernate.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.hibernate.curso.jpa2.modelo.Fabricante;

public class FabricanteDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public void salvar(Fabricante fabricante){
		entityManager.merge(fabricante);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos(){
		return entityManager.createQuery("from Fabricante").getResultList();
	}

	public void excluir(Fabricante fabricante) {
		fabricante = entityManager.find(Fabricante.class, fabricante.getCodigo());
		entityManager.remove(fabricante);
		entityManager.flush();
	}

	public Fabricante buscarPeloCodigo(Long id) {
		return entityManager.find(Fabricante.class, id);
	}

}
