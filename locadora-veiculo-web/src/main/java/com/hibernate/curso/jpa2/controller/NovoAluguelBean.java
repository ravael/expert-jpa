package com.hibernate.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.curso.jpa2.modelo.Aluguel;
import com.hibernate.curso.jpa2.modelo.ApoliceSeguro;
import com.hibernate.curso.jpa2.modelo.Carro;
import com.hibernate.curso.jpa2.modelo.Motorista;
import com.hibernate.curso.jpa2.service.AluguelService;
import com.hibernate.curso.jpa2.service.CarroService;
import com.hibernate.curso.jpa2.service.MotoristaService;
import com.hibernate.curso.jpa2.service.NegocioException;
import com.hibernate.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class NovoAluguelBean implements Serializable{

	
	private Aluguel aluguel;
	
	private List<Carro> carros;
	
	@Inject
	private CarroService carroService;
	
	private List<Motorista> motoristas;
	
	@Inject
	private MotoristaService motoristaService;
	
	@Inject
	private AluguelService aluguelService;
	
	@PostConstruct
	public void init(){
		this.limpar();
		this.carros = carroService.buscaTodos(); 
	}
	
	public void salvar(){
		try {
			this.aluguelService.salvar(aluguel);
			FacesUtil.addSuccessMessage("Aluguel salvo com sucesso");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void limpar(){
		this.aluguel = new Aluguel();
		this.aluguel.setApoliceSeguro(new ApoliceSeguro());
		this.motoristas = motoristaService.buscaTodosMotorista();
	}
	
	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public CarroService getCarroService() {
		return carroService;
	}

	public void setCarroService(CarroService carroService) {
		this.carroService = carroService;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public void setMotoristas(List<Motorista> motoristas) {
		this.motoristas = motoristas;
	}
	
	
}
