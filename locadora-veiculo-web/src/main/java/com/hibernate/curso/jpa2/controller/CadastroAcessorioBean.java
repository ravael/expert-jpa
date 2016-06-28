package com.hibernate.curso.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.curso.jpa2.modelo.Acessorio;
import com.hibernate.curso.jpa2.service.AcessorioService;
import com.hibernate.curso.jpa2.service.NegocioException;
import com.hibernate.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAcessorioBean implements Serializable{
			 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2018189504469945295L;
	
	private Acessorio acessorio;

	@Inject
	private AcessorioService acessorioService;
	
	public void limpar(){
		this.acessorio = new Acessorio();
	}
	
	@PostConstruct
	public void init(){
		limpar();
	}
	
	
	public void salvar(){
		try {
			acessorioService.salvar(acessorio);
			FacesUtil.addSuccessMessage("Acessorio cadastrado com sucesso");
			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}
}
