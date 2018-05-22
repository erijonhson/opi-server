package com.ufcg.opi.unit;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ufcg.opi.models.Delegate;
import com.ufcg.opi.models.School;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolTest {

	@Test
	public void constructorTest() {
		School school = new School("Test School", "Test City", new HashSet<>(), new Delegate(), new HashSet<>());

		assertEquals(school.getName(), "Test School");
	}

}

