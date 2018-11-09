package com.nelioalves.cursomc.dto;

import java.io.Serializable;

public class ItemProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer quantidade;
	private ProdutoDTO produto;

	public ItemProdutoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemProdutoDTO(ProdutoDTO produto, Integer quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

}
