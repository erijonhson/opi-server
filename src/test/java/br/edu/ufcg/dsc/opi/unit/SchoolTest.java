package br.edu.ufcg.dsc.opi.unit;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ufcg.dsc.opi.delegate.DelegateModel;
import br.edu.ufcg.dsc.opi.school.SchoolModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolTest {

	@Test
	public void constructorTest() {
		SchoolModel school = new SchoolModel("Test School", "Test City", new HashSet<>(), new DelegateModel(), new HashSet<>());

		assertEquals(school.getName(), "Test School");
	}

}

