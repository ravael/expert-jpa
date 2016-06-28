package com.hibernate.curso.jpa2.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.hibernate.curso.jpa2.modelo.Carro;

public class CarroDAO {
	
	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Carro> buscaTodos(){
		return entityManager.createQuery("from Carro").getResultList();
	}
	
	public void salvar(Carro carro){
		entityManager.merge(carro);
	}

	public Carro buscarPeloCodigo(Long codigo) {
		return entityManager.find(Carro.class, codigo);
	}

	public void excluir(Carro carroSelecionado) {
		carroSelecionado = entityManager.find(Carro.class, carroSelecionado.getCodigo());
		entityManager.remove(carroSelecionado);
		entityManager.flush();
		
	}

	public Carro buscarCarroComAcessorios(Long codigo) {
		return (Carro) entityManager.createQuery("select c from Carro c JOIN c.acessorios a where c.codigo = :codigo")
				.setParameter("codigo", codigo)
				.getSingleResult();
		
	}
}

