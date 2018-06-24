package br.edu.ufcg.dsc.opi.security;

public class Payload {

	private String username;
	private String[] roles;

	public Payload(String username, String[] roles) {
		this.setUsername(username);
		this.setRoles(roles);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}
