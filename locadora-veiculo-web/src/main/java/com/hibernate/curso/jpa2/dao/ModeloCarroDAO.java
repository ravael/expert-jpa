package com.hibernate.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.hibernate.curso.jpa2.modelo.ModeloCarro;
import com.hibernate.curso.jpa2.service.NegocioException;
import com.hibernate.curso.jpa2.util.jpa.Transactional;

public class ModeloCarroDAO implements Serializable{
	
	@Inject
	private EntityManager entityManager;

	public ModeloCarro buscarPeloCodigo(Long codigo){
		return entityManager.find(ModeloCarro.class, codigo);
	}
	
	public void salvar(ModeloCarro modelo){
		entityManager.merge(modelo);
	}
	
	@SuppressWarnings("unchecked")
	public List<ModeloCarro> buscarTodos(){
		return entityManager.createQuery("from ModeloCarro").getResultList();
	}
	
	@Transactional
	public void excluir(ModeloCarro modeloCarro) throws NegocioException{
		modeloCarro = buscarPeloCodigo(modeloCarro.getCodigo());
		entityManager.remove(modeloCarro);
		entityManager.flush();
	}
}
