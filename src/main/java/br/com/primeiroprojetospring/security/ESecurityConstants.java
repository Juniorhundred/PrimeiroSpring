package br.com.primeiroprojetospring.security;

public enum ESecurityConstants {
	//Chave SECRET
	SECRET("primeiroProjetoSpring"),
	//PREFIXO BEARER
	TOKEN_PREFIX("Bearer "),
	// TAG HEADER
	HEADER("Authorization");
	
	//TEMPO EM MILISEGUNDOS EXPIRAR TOKEN 600000 = 10min
	
	static final long EXPIRATION_TIME = 6000001;

	private String description;
	
	private ESecurityConstants(String descripition) {
		this.description = descripition;
	}
	
	public String desc () {
		return this.description;
	}

}