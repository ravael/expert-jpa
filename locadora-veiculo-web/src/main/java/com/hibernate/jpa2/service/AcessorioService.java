package com.hibernate.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.hibernate.jpa2.dao.AcessorioDAO;
import com.hibernate.jpa2.modelo.Acessorio;
import com.hibernate.jpa2.util.jpa.Transactional;

public class AcessorioService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7246437534467286307L;
	
	
	@Inject
	public AcessorioDAO acessorioDao;
	
	@Transactional
	public void salvar(Acessorio acessorio)throws NegocioException{
		acessorioDao.salvar(acessorio);
	}

}
