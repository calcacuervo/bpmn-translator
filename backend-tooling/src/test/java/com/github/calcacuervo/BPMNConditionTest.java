package com.github.calcacuervo;

import org.jbpm.bpmn2.xml.XmlBPMNProcessDumper;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.jbpm.ruleflow.core.factory.SplitFactory;
import org.jbpm.workflow.core.node.Split;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BPMNConditionTest {

	@Test
	public void simpleTestForCreatingCondition() {
		RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("org.jbpm.process");

		factory.name("My process").packageName("org.jbpm").startNode(1).name("Start").done();

		SplitFactory splitFactory = factory.splitNode(20);
		
		splitFactory.constraint(3, "percentageComparison", "tFormalExpression", "java", "salaryPercentage>2")
				.type(Split.TYPE_XOR);

		
		splitFactory.constraint(4, "percentageComparison", "tFormalExpression", "java", "salaryPercentage<=2")
				.type(Split.TYPE_XOR);
		splitFactory.done();

		factory.endNode(3).name("End1").done();
		factory.endNode(4).name("End2").done();

		factory.connection(1, 20);
		factory.connection(20, 3);
		factory.connection(20, 4);

		RuleFlowProcess process = factory.validate().getProcess();
		System.out.println(XmlBPMNProcessDumper.INSTANCE.dump(process));
	}

}
