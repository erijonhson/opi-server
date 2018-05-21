package com.ufcg.opi.models;

/**
 * Category of the OPI, namely: <br>
 * - Iniciação 1 <br>
 * - Iniciação 1 Escolas Públicas <br>
 * - Iniciação 2 <br>
 * - Iniciação 2 Escolas Públicas <br>
 * - Programação <br>
 * - Avançado Júnior <br>
 * - Avançado Sênior <br>
 * 
 * @author Eri Jonhson
 */
public enum OpiCategory {

	Iniciação_1 ("Iniciação 1"),
	Iniciação_1_Pub ("Iniciação 1 Escolas Públicas"),
	Iniciação_2 ("Iniciação 2"),
	Iniciação_2_Pub ("Iniciação 2 Escolas Públicas"),
	Programação ("Programação"),
	Avançado_Júnior ("Avançado Júnior"),
	Avançado_Sênior ("Avançado Sênior");

	private String name;

	private OpiCategory(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
