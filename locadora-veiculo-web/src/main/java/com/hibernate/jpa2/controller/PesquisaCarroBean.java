package com.hibernate.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.jpa2.dao.CarroDAO;
import com.hibernate.jpa2.modelo.Carro;
import com.hibernate.jpa2.modeloLazy.LazyCarroDataModel;
import com.hibernate.jpa2.service.CarroService;
import com.hibernate.jpa2.service.NegocioException;
import com.hibernate.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCarroBean implements Serializable{

	private List<Carro> carros;
	
	@Inject
	private CarroService carroService;
	
	private LazyCarroDataModel lazyCarros;
	
	@Inject
	private CarroDAO carroDao;
	
	private Carro carroSelecionado;
	
	public void buscarCarroComAcessorios(){
		carroSelecionado = carroService.buscarCarroComAcessorios(carroSelecionado);
	}
	
	public void excluir(){
		try {
			this.carroService.excluir(carroSelecionado);
			FacesUtil.addSuccessMessage("Carro excluido com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init(){
		this.lazyCarros = new  LazyCarroDataModel(carroDao);
//		this.carros = carroService.buscaTodos();
		
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public LazyCarroDataModel getLazyCarros() {
		return lazyCarros;
	}

	public void setLazyCarros(LazyCarroDataModel lazyCarros) {
		this.lazyCarros = lazyCarros;
	}
}
