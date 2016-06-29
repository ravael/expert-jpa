package com.hibernate.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.hibernate.jpa2.dao.ModeloCarroDAO;
import com.hibernate.jpa2.modelo.ModeloCarro;
import com.hibernate.jpa2.util.jpa.Transactional;

public class CadastroModeloService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3194641180191682007L;
	@Inject
	private ModeloCarroDAO modeloCarroDao;

	@Transactional
	public void salvar(ModeloCarro modeloCarro)throws NegocioException{
		if(modeloCarro.getDescricao() == null || modeloCarro.getDescricao().trim().equals("")){
			throw new NegocioException("A descricao e obrigatoria.");
		}
		if(modeloCarro.getFabricante() == null){
			throw new NegocioException("O fabricante e obrigatorio");
		}
		modeloCarroDao.salvar(modeloCarro);
	}
}
