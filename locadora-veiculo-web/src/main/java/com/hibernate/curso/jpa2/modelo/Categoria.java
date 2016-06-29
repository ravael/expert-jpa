package com.hibernate.curso.jpa2.modelo;

public enum Categoria {

	HATCH_COMPACTO(1L, "Carro hatch"),
	HATCH_MEDIO(2L, "Carro médio"),
	SEDAN_COMPACTO(3L, "Carro campacto"),
	SEDAN_MEDIO(4L, "Sedan médio"),
	SEDAN_GRANDE(5L, "Sedan grande"),
	MINIVAN(6L, "Minivan"),
	ESPORTIVO(7L, "Esportivo"),
	UTILITARIO_COMERCIAL(8L, "Utilitário comercial");
	

	private Long codigo;
	
	private String descricao;
	
	
	Categoria (Long codigo, String descricao){
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
