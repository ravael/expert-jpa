package com.hibernate.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.hibernate.jpa2.dao.MotoristaDAO;
import com.hibernate.jpa2.modelo.Motorista;
import com.hibernate.jpa2.util.jpa.Transactional;

public class CadastroMotoristaService implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MotoristaDAO motoristaDao;
	
	@Transactional
	public void salvar(Motorista motorista) throws NegocioException{
		motoristaDao.salvar(motorista);
	}
	
	@Transactional
	public List<Motorista> buscaTodos(){
		return motoristaDao.buscaTodos();
	}

	@Transactional
	public void excluir(Motorista motorista){
		motoristaDao.excluir(motorista);
	}
	
	@Transactional
	public Motorista buscaMotoristaPorCodigo(Long codigo){
		return motoristaDao.buscarPeloCodigo(codigo);
	}
	
}
