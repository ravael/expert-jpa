package com.hibernate.curso.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.curso.jpa2.dao.FabricanteDAO;
import com.hibernate.curso.jpa2.modelo.Categoria;
import com.hibernate.curso.jpa2.modelo.Fabricante;
import com.hibernate.curso.jpa2.modelo.ModeloCarro;
import com.hibernate.curso.jpa2.service.CadastroModeloService;
import com.hibernate.curso.jpa2.service.NegocioException;
import com.hibernate.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroModeloCarroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ModeloCarro modeloCarro;
	
	private List<Fabricante> fabricantes;
	
	@Inject
	private CadastroModeloService cadastroModeloCarroService;
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	private List<Categoria> categorias;
	
	public void salvar() {
		try {
			this.cadastroModeloCarroService.salvar(modeloCarro);
			FacesUtil.addSuccessMessage("Modelo carro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.fabricantes = fabricanteDAO.buscarTodos();
		this.categorias = Arrays.asList(Categoria.values());
	}
	
	public void limpar() {
		this.modeloCarro = new ModeloCarro();
	}

	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
