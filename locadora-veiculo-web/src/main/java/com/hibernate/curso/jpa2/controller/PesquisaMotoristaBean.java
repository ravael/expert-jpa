package com.hibernate.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.curso.jpa2.modelo.Motorista;
import com.hibernate.curso.jpa2.service.CadastroMotoristaService;

@Named
@ViewScoped
public class PesquisaMotoristaBean implements Serializable{

	private List<Motorista> motoristas;
	
	private Motorista motoristaSelecionado;
	
	@Inject
	private CadastroMotoristaService motoristaService;
	
	public void excluir(){
		motoristaService.excluir(motoristaSelecionado);
		motoristas.remove(motoristaSelecionado);
	}
	
	@PostConstruct	
	public void init(){
		this.motoristas = motoristaService.buscaTodos();	
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public void setMotoristas(List<Motorista> motoristas) {
		this.motoristas = motoristas;
	}

	public Motorista getMotoristaSelecionado() {
		return motoristaSelecionado;
	}

	public void setMotoristaSelecionado(Motorista motoristaSelecionado) {
		this.motoristaSelecionado = motoristaSelecionado;
	}
	
		
}
