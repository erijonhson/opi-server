package br.edu.ufcg.dsc.opi.util;

public class NotFoundRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -2187500009249884414L;
	public NotFoundRuntimeException() {
		super("Recurso não encontrado");
	}
}
