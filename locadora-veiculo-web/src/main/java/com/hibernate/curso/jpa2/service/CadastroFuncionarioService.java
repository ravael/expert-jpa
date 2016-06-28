package com.hibernate.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.hibernate.curso.jpa2.dao.FuncionarioDAO;
import com.hibernate.curso.jpa2.modelo.Funcionario;
import com.hibernate.curso.jpa2.util.jpa.Transactional;

public class CadastroFuncionarioService implements Serializable{
	
	@Inject
	private FuncionarioDAO funcionarioDao;

	@Transactional
	public void salvar(Funcionario funcionario) throws NegocioException{
		funcionarioDao.salvar(funcionario);
		
	}

}
