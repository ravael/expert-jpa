package com.hibernate.jpa2.test.model;

import java.math.BigDecimal;

public class CarroDTO {
	
	private String modelo;
	private String placa;
	private BigDecimal valor;
	
	public CarroDTO(String modelo, String placa, BigDecimal valor) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.valor = valor;
	}
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	

}
