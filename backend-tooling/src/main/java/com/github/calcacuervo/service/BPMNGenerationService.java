package com.github.calcacuervo.service;

/**
 * Service to generate BPMN from a {@link com.github.calcacuervo.model.Process}.
 * 
 * @author calcacuervo
 * 
 */
public interface BPMNGenerationService {

	/**
	 * Generates the BPMN representation of the given Process.
	 * 
	 * @param process
	 * @return
	 */
	public String generateBPMN(com.github.calcacuervo.model.Process process);
	
}
