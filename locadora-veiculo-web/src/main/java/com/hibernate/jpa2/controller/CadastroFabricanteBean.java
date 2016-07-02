package com.hibernate.jpa2.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.jpa2.modelo.Fabricante;
import com.hibernate.jpa2.service.CadastroFabricanteService;
import com.hibernate.jpa2.service.NegocioException;
import com.hibernate.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable{

	@Inject
	private CadastroFabricanteService cadastroFabricanteService;
	
	private Fabricante fabricante = new Fabricante();
	
	public void salvar() {
		try {
			this.cadastroFabricanteService.salvar(fabricante);
			FacesUtil.addSuccessMessage("Fabricante salvo com sucesso!");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void limpar(){
		this.fabricante = new Fabricante();
	}
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	
}
