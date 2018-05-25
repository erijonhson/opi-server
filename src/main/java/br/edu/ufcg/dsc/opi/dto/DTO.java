package br.edu.ufcg.dsc.opi.dto;

/**
 * Defines interface to transfer objects.
 * 
 * @author Eri Jonhson
 *
 * @param <T>
 */
public interface DTO<T> {

	public T toModel();

}
