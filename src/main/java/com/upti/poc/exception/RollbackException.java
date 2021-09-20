package com.upti.poc.exception;

public class RollbackException extends RuntimeException {

	/** * */
	private static final long serialVersionUID = 1L;

	public RollbackException() {
		super();
	}

	public RollbackException(String mensagem) {
		super(mensagem);
	}
}
