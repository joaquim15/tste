package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import java.util.List;

public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ItemProdutoDTO> items;
	private String token;
	private String hash;
	private String method;
	private Double total;
	private PedidoDTO pedido;

	public PaymentDTO() {
	}

	public PaymentDTO(List<ItemProdutoDTO> items, String token, String hash, String method, Double total,
			PedidoDTO pedido) {
		super();
		// this.items = items;
		this.token = token;
		this.hash = hash;
		this.method = method;
		this.total = total;
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "PaymentDTO [token=" + token + ", hash=" + hash + ", method=" + method + ", total=" + total + ", pedido="
				+ pedido + ", items=" + items + "]";
	}

	public List<ItemProdutoDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemProdutoDTO> items) {
		this.items = items;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public PedidoDTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}

}
