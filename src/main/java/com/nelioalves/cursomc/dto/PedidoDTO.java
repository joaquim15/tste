package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import java.util.List;

public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClienteDTO cliente;
	private EnderecoDTO enderecoDeEntrega;
	private PagamentoDTO pagamento;
	private List<ItemProdutoDTO> itens;

	public PedidoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PedidoDTO(ClienteDTO cliente, EnderecoDTO enderecoDeEntrega, PagamentoDTO pagamento, List<ItemProdutoDTO> itens) {
		super();
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.pagamento = pagamento;
		this.itens = itens;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public EnderecoDTO getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(EnderecoDTO enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public PagamentoDTO getPagamento() {
		return pagamento;
	}

	public void setPagemento(PagamentoDTO pagemento) {
		this.pagamento = pagemento;
	}

	public List<ItemProdutoDTO> getItens() {
		return itens;
	}

	public void setItems(List<ItemProdutoDTO> itens) {
		this.itens = itens;
	}

}
