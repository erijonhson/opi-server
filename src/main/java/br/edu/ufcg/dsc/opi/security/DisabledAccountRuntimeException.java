package br.edu.ufcg.dsc.opi.security;

public class DisabledAccountRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 524819519925891459L;

	public DisabledAccountRuntimeException() {
		// TODO: internacionalization
		super("Conta desabilitada, contate administrador");
	}

}
