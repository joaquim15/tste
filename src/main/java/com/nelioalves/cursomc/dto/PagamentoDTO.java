package com.nelioalves.cursomc.dto;

import java.io.Serializable;

public class PagamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;
	private String valorPagamento;
	private String type;

	public PagamentoDTO() {

	}

	public PagamentoDTO(Integer numeroDeParcelas, String valorPagamento, String type) {
		super();
		this.numeroDeParcelas = numeroDeParcelas;
		this.valorPagamento = valorPagamento;
		this.type = type;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
