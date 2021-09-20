package com.upti.poc.exception;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegocioException() {
		super();
	}

	public NegocioException(String mensagem) {
		super(mensagem);
	}

}
