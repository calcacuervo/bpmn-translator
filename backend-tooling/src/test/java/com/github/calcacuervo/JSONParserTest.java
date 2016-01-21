package com.github.calcacuervo;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.github.calcacuervo.persistence.Workflow;
import com.github.calcacuervo.service.PersistenceManager;
import com.github.calcacuervo.service.impl.PersistenceManagerImpl;

/**
 * Unit test for Json Parser.
 */
public class JSONParserTest {

	@Test
	public void serializeTest() throws JsonGenerationException, JsonMappingException, IOException {
		PersistenceManager manager = new PersistenceManagerImpl();
		File jsonFile = new File("src/test/resources/jsonparsertest-input.json");
		Workflow persistenceProcess = manager.parse(jsonFile);
		System.out.println(persistenceProcess);
	}
	
}
