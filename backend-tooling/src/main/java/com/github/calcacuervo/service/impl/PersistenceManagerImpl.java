package com.github.calcacuervo.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.github.calcacuervo.persistence.Workflow;
import com.github.calcacuervo.service.PersistenceManager;

public class PersistenceManagerImpl implements PersistenceManager {
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public File persist(Workflow persistenceProcess, String outputFilePath) throws IOException {
		File outputFile = new File(outputFilePath);
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, persistenceProcess);
		} catch (JsonGenerationException | JsonMappingException e) {
			throw new RuntimeException("There was a problem persisting process", e);
		}
		return outputFile;
	}

	@Override
	public Workflow parse(File jsonFile) throws IOException {
		Workflow persistenceProcess = null;
		try {
			persistenceProcess = mapper.readValue(jsonFile, Workflow.class);
		} catch (JsonGenerationException | JsonMappingException | JsonParseException e) {
			throw new RuntimeException("There was a problem persisting process", e);
		}
		return persistenceProcess;
	}

}
