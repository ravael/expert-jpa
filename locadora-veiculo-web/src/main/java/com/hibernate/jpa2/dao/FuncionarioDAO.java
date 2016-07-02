package com.hibernate.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.hibernate.jpa2.modelo.Funcionario;
import com.hibernate.jpa2.service.NegocioException;

public class FuncionarioDAO implements Serializable{
	
	@Inject
	private EntityManager entityManager;

	public Funcionario buscarPeloCodigo(Long codigo) {
		return entityManager.find(Funcionario.class, codigo);
	}
	
	public void salvar(Funcionario funcionario){
		entityManager.merge(funcionario);
	}

	public void excluir(Funcionario funcionarioSelecionado) throws NegocioException{
		funcionarioSelecionado = entityManager.find(Funcionario.class, funcionarioSelecionado.getCodigo());
		entityManager.remove(funcionarioSelecionado);
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarTodos() {
		return entityManager.createQuery("from Funcionario").getResultList();
	}

}
