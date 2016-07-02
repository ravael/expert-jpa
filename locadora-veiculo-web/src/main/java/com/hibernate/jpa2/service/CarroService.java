package com.hibernate.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.hibernate.jpa2.dao.CarroDAO;
import com.hibernate.jpa2.modelo.Carro;
import com.hibernate.jpa2.util.jpa.Transactional;

public class CarroService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarroDAO carroDao;
	
	@Transactional
	public void salvar(Carro carro) throws NegocioException{
		carroDao.salvar(carro);
	}
	
	@Transactional
	public List<Carro> buscaTodos(){
		return carroDao.buscaTodos();
	}

	@Transactional
	public void excluir(Carro carroSelecionado) throws NegocioException {
		carroDao.excluir(carroSelecionado);
		
	}
	
	@Transactional
	public Carro buscarCarroComAcessorios(Carro carro){
		return carroDao.buscarCarroComAcessorios(carro.getCodigo());
	}
	
}
