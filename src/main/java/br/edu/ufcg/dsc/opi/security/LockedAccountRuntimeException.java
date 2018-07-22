package br.edu.ufcg.dsc.opi.security;

public class LockedAccountRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -2750802310221290909L;

	public LockedAccountRuntimeException() {
		// TODO: internacionalization
		super("Conta bloqueada, contate administrador");
	}

}
