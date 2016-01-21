package com.github.calcacuervo.service.impl;

import org.jbpm.bpmn2.xml.XmlBPMNProcessDumper;
import org.jbpm.ruleflow.core.RuleFlowProcess;

import com.github.calcacuervo.service.BPMNGenerationService;
import com.github.calcacuervo.service.visitor.ProcessGeneratorVisitor;

public class BPMNGenerationServiceImpl implements BPMNGenerationService {

	@Override
	public String generateBPMN(com.github.calcacuervo.model.Process modelProcesss) {
		
		ProcessGeneratorVisitor visitor = new ProcessGeneratorVisitor();
		visitor.visit(modelProcesss);
		
		RuleFlowProcess process = visitor.getFactory().validate().getProcess();
		return XmlBPMNProcessDumper.INSTANCE.dump(process);
	}

}
