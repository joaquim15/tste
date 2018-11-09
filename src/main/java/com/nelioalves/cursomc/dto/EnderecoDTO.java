package com.nelioalves.cursomc.dto;

import java.io.Serializable;

public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	public EnderecoDTO() {
		super();
	}

	public EnderecoDTO(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
