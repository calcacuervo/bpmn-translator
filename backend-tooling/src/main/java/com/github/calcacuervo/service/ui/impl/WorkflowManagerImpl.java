package com.github.calcacuervo.service.ui.impl;

import com.github.calcacuervo.persistence.Workflow;
import com.github.calcacuervo.service.ui.WorkflowManager;

public class WorkflowManagerImpl implements WorkflowManager {

	public String processWorkflow(String jsonExpression) {
		Workflow workflow = new Workflow();
		return workflow.toString();
	}

}
