package com.github.calcacuervo.service.visitor;

import org.drools.core.process.core.datatype.DataType;
import org.jbpm.bpmn2.xml.XmlBPMNProcessDumper;
import org.jbpm.process.core.context.variable.Variable;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;

public class ExtendedProcessFactory extends RuleFlowProcessFactory{

	 public static ExtendedProcessFactory createProcess(String id) {
	        return new ExtendedProcessFactory(id);
	    }
	 
	protected ExtendedProcessFactory(String id) {
		super(id);
	}

	@Override
	public RuleFlowProcessFactory variable(String name, DataType type, Object value) {
    	Variable variable = new Variable();
    	variable.setName(name);
    	variable.setType(type);
    	variable.setValue(value);

    	variable.setMetaData("ItemSubjectRef", "_" + XmlBPMNProcessDumper.replaceIllegalCharsAttribute(variable.getName()));
    	getRuleFlowProcess().getVariableScope().getVariables().add(variable);
        return this;
    }
}
