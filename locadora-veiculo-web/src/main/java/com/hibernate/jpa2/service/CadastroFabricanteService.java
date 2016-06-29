package com.hibernate.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.hibernate.jpa2.dao.FabricanteDAO;
import com.hibernate.jpa2.modelo.Fabricante;
import com.hibernate.jpa2.util.jpa.Transactional;

public class CadastroFabricanteService implements Serializable{
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@Transactional
	public void salvar(Fabricante fabricante) throws NegocioException {
		if (fabricante.getNome() == null || fabricante.getNome().trim().equals("")) { 
			throw new NegocioException("O nome do fabricante é obrigatório");
		}
		
		this.fabricanteDAO.salvar(fabricante);
	}
	
	@Transactional
	public List<Fabricante> buscaTodos(){
		return fabricanteDAO.buscarTodos();
	}
	
	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException{
		fabricanteDAO.excluir(fabricante);
	}
}
