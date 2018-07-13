package br.edu.ufcg.dsc.opi.unit;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ufcg.dsc.opi.OpiServerApplication;
import br.edu.ufcg.dsc.opi.TestJPAConfig;
import br.edu.ufcg.dsc.opi.school.SchoolModel;
import br.edu.ufcg.dsc.opi.util.user.UserFactory;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { OpiServerApplication.class,
		TestJPAConfig.class })
public class SchoolTest {

	@Test
	public void constructorTest() {
		SchoolModel school = new SchoolModel("Test School", "Test City", new HashSet<>(), UserFactory.createDelegateObject(),
				new HashSet<>());

		assertEquals(school.getName(), "Test School");
	}

}
