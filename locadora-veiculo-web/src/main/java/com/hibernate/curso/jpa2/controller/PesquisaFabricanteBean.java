package com.hibernate.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.curso.jpa2.modelo.Fabricante;
import com.hibernate.curso.jpa2.service.CadastroFabricanteService;
import com.hibernate.curso.jpa2.service.NegocioException;
import com.hibernate.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable{

	
	@Inject
	private CadastroFabricanteService cadastroFabricanteService;
	
	private Fabricante fabricanteSelecionado;
	
	private List<Fabricante> fabricantes = new ArrayList<Fabricante>();
	
	
	@PostConstruct
	public void init(){
		this.fabricantes = listaFabricantes();
	}
	
	public List<Fabricante> listaFabricantes(){
		return cadastroFabricanteService.buscaTodos();
	}
	
	public void excluir() {
		try {
			cadastroFabricanteService.excluir(fabricanteSelecionado);
			this.fabricantes.remove(fabricanteSelecionado);
			FacesUtil.addSuccessMessage("Fabricante " + fabricanteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	
	
	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

	
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}


	

}
