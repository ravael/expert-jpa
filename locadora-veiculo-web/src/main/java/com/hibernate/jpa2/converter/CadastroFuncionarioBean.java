package com.hibernate.jpa2.converter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hibernate.jpa2.modelo.Funcionario;
import com.hibernate.jpa2.modelo.Sexo;
import com.hibernate.jpa2.service.CadastroFuncionarioService;
import com.hibernate.jpa2.service.NegocioException;
import com.hibernate.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	
	private List<Sexo> sexos;
	
	@Inject
	private CadastroFuncionarioService cadastroFuncionarioService;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
	}
	
	public void salvar() {
		try {
			this.cadastroFuncionarioService.salvar(funcionario);
			FacesUtil.addSuccessMessage("Funcionário salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	public void limpar() {
		this.funcionario = new Funcionario();
		this.sexos = Arrays.asList(Sexo.values());
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

	public void setSexos(List<Sexo> sexos) {
		this.sexos = sexos;
	}
}