package com.github.calcacuervo.service.ui.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.github.calcacuervo.service.ui.ConditionManager;
import com.github.calcacuervo.service.ui.impl.ConditionManagerImpl;

/**
 * Unit test for simple App..
 */
public class ConditionManagerImplTest {

	@Test
	public void conditionTest() throws IOException {
		ConditionManager service = new ConditionManagerImpl();
		String script = readFile("src/test/resources/expression_message.json");
		script = service.processCondition(script);
		
		System.out.println(script);

	}

	private String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}

		reader.close();
		return stringBuilder.toString();
	}

}