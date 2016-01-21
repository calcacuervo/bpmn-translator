package com.github.calcacuervo;

import org.jbpm.bpmn2.xml.XmlBPMNProcessDumper;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BPMNGenerationTest {		

	@Test
	public void simpleTestForCreatingBPMN() {
		RuleFlowProcessFactory factory = RuleFlowProcessFactory
				.createProcess("org.jbpm.process");
		factory
		// header
		.name("My process")
				.packageName("org.jbpm")
				// nodes
				.startNode(1).name("Start").done().humanTaskNode(14)
				.actorId("mariano").taskName("taskName").done().actionNode(2)
				.name("Action")
				.action("java", "System.out.println(\"Action\");").done().connection(1, 2)
				.endNode(3).name("End").done()
				// connections
				.connection(2, 14).connection(14, 3);
		RuleFlowProcess process = factory.validate().getProcess();
		System.out.println(XmlBPMNProcessDumper.INSTANCE.dump(process));
	}
	
}
