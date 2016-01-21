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
	public File persist(Workflow persistenceProcess, String outputFilePath) {
		File outputFile = new File(outputFilePath);
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, persistenceProcess);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputFile;
	}

	@Override
	public Workflow parse(File jsonFile) {
		Workflow persistenceProcess = null;
		try {
			persistenceProcess = mapper.readValue(jsonFile, Workflow.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persistenceProcess;
	}

}
