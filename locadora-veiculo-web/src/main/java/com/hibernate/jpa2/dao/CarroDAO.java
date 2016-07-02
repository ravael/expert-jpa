package com.hibernate.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.hibernate.jpa2.modelo.Carro;

public class CarroDAO implements Serializable{
	
	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Carro> buscaTodos(){
		return entityManager.createNamedQuery("Carro.buscarTodos").getResultList();
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
		return (Carro) entityManager.createNamedQuery("Carro.buscarCarroComACessorios")
				.setParameter("codigo", codigo)
				.getSingleResult();
		
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscaComPaginacao(int first, int pageSize) {
		return entityManager.createNamedQuery("Carro.buscarTodos")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Long encontrarQuantidadeDeCarros() {
		return entityManager.createQuery("select count(c) from Carro c", Long.class).getSingleResult();
	}
	
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
}

