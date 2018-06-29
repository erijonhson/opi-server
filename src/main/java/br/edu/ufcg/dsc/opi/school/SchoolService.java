package br.edu.ufcg.dsc.opi.school;

import java.util.Collection;

public interface SchoolService {

	public SchoolModel create(SchoolModel school);

	public Collection<SchoolDTO> index();

}
