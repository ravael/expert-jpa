package com.hibernate.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.jpa2.dao.CarroDAO;
import com.hibernate.jpa2.modelo.dto.AluguelCarroDTO;

@Named
@ViewScoped
public class RelatorioTotalAlgueisPorCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroDAO carroDAO;
	
	private List<AluguelCarroDTO> totalDeAlugueisPorCarro;
	
	public void buscar() {
		totalDeAlugueisPorCarro = this.carroDAO.buscarTotalAlugueisPorCarro();
	}

	public List<AluguelCarroDTO> getTotalDeAlugueisPorCarro() {
		return totalDeAlugueisPorCarro;
	}
	
}
