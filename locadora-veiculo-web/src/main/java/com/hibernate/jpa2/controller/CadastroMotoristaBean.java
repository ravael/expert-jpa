package com.hibernate.jpa2.controller;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.jpa2.modelo.Motorista;
import com.hibernate.jpa2.modelo.Sexo;
import com.hibernate.jpa2.service.CadastroMotoristaService;
import com.hibernate.jpa2.service.NegocioException;
import com.hibernate.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMotoristaBean implements Serializable{

	private Motorista motorista;
	
	@Inject
	private CadastroMotoristaService cadastroMotoristaService;
	
	private List<Sexo> sexos;
	
	public void salvar(){
		try {
			this.cadastroMotoristaService.salvar(motorista);
			FacesUtil.addSuccessMessage("Motorista cadastrado com sucesso");
			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init(){
		limpar();
	}
	
	public void limpar(){
		this.motorista = new Motorista();
		this.sexos = Arrays.asList(Sexo.values());
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

	public void setSexos(List<Sexo> sexos) {
		this.sexos = sexos;
	}
}
