package com.nelioalves.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoEmDinheiro")
public class PagamentoEmDinheiro extends Pagamento {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	private String valor;

	public PagamentoEmDinheiro() {
		super();
	}

	public PagamentoEmDinheiro(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento, String valor) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.valor = valor;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
