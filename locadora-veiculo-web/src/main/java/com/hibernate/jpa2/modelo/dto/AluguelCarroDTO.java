package com.hibernate.jpa2.modelo.dto;

import java.io.Serializable;

public class AluguelCarroDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -398859653600397902L;
	
	
	private String placa;
	private Long totalDeAlugueis;
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Long getTotalDeAlugueis() {
		return totalDeAlugueis;
	}
	public void setTotalDeAlugueis(Long totalDeAlugueis) {
		this.totalDeAlugueis = totalDeAlugueis;
	}
}
