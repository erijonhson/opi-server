package br.edu.ufcg.dsc.opi.security;

/**
 * User security data transfer object interface. All users who can log into the
 * system must implement it into a default data transfer object.
 * 
 * @author Eri Jonhson
 */
public interface UserDTO {

	/**
	 * Includes security key into DTO.
	 * 
	 * @param jwt
	 */
	public void setToken(String jwt);

	/**
	 * Includes security refresh key into DTO.
	 * 
	 * @param refresh
	 */
	public void setRefreshToken(String refresh);

}
