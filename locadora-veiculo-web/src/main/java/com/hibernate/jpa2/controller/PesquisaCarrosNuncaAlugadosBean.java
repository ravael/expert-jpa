package com.hibernate.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.jpa2.dao.CarroDAO;
import com.hibernate.jpa2.modelo.Carro;

@Named
@ViewScoped
public class PesquisaCarrosNuncaAlugadosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Carro> carros;
	
	@Inject
	private CarroDAO carroDAO;
	
	public void buscarCarrosNuncaAlugados() {
		this.carros = this.carroDAO.buscaCarrosNuncaAlugados();
	}

	public List<Carro> getCarros() {
		return carros;
	}
	
}
