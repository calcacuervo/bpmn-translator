package com.github.calcacuervo.service;

import java.io.File;

import com.github.calcacuervo.persistence.Workflow;

public interface PersistenceManager {

	File persist(Workflow persistenceProcess, String outputFilePath);

	Workflow parse(File jsonFile);

}
