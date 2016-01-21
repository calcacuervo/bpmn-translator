package com.github.calcacuervo.service;

import java.io.File;
import java.io.IOException;

import com.github.calcacuervo.persistence.Workflow;

/**
 * Service to persist processes.
 * @author calcacuervo
 *
 */
public interface PersistenceManager {

	/**
	 * Persists a given process to a given path.
	 * @param persistenceProcess
	 * @param outputFilePath
	 * @return
	 * @throws IOException
	 */
	File persist(Workflow persistenceProcess, String outputFilePath) throws IOException;

	/**
	 * Parses a process from a file.
	 * @param jsonFile
	 * @return
	 * @throws IOException 
	 */
	Workflow parse(File jsonFile) throws IOException;

}
