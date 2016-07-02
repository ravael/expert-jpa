package com.hibernate.jpa2.service;

import java.io.Serializable;
import java.util.Calendar;

import javax.inject.Inject;

import com.hibernate.jpa2.dao.AluguelDAO;
import com.hibernate.jpa2.modelo.Aluguel;
import com.hibernate.jpa2.util.jpa.Transactional;

public class AluguelService implements Serializable{

	@Inject
	private AluguelDAO aluguelDao;
	
	@Transactional
	public void salvar(Aluguel aluguel)throws NegocioException{
		
		aluguel.setDataPedido(Calendar.getInstance());
		
		aluguelDao.salvar(aluguel);
	}
}
