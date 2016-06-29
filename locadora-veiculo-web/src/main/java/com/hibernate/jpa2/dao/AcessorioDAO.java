package com.hibernate.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.hibernate.jpa2.modelo.Acessorio;
import com.hibernate.jpa2.service.NegocioException;

public class AcessorioDAO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4826311104769722613L;
	
	@Inject
	private EntityManager entityManager;

	public Acessorio buscarPeloCodigo(Long codigo) {
		return entityManager.find(Acessorio.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Acessorio> buscarTodos() {
		return entityManager.createQuery("from Acessorio").getResultList();
	}

	public void salvar(Acessorio acessorio) {
		entityManager.merge(acessorio);
		
	}

	public void excluir(Acessorio acessorioSelecionado) throws NegocioException {
		acessorioSelecionado = entityManager.find(Acessorio.class, acessorioSelecionado.getCodigo());
		entityManager.remove(acessorioSelecionado);
		entityManager.flush();
		
	}
	

}
