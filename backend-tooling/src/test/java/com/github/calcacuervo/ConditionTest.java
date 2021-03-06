package com.github.calcacuervo;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.workflow.core.node.Split;
import org.junit.Test;

import com.github.calcacuervo.model.Condition;
import com.github.calcacuervo.model.EndNode;
import com.github.calcacuervo.model.Gateway;
import com.github.calcacuervo.model.Process;
import com.github.calcacuervo.model.Property;
import com.github.calcacuervo.model.StartNode;
import com.github.calcacuervo.model.Transition;
import com.github.calcacuervo.service.BPMNGenerationService;
import com.github.calcacuervo.service.impl.BPMNGenerationServiceImpl;

/**
 * Unit test for simple App..
 */
public class ConditionTest {

	@Test
	public void conditionTest() {
		BPMNGenerationService service = new BPMNGenerationServiceImpl();

		//Process Variables
		List<Property> processVariables = new ArrayList<Property>();
		processVariables.add(new Property(new String("robben"), "arjen"));
		processVariables.add(new Property(new Float(22.2), "doubleValue"));
		
		// Create nodes
		StartNode startNode = new StartNode(new Long(1));
		EndNode endNode1 = new EndNode(new Long(5), "EndNode1");
		EndNode endNode2 = new EndNode(new Long(6), "EndNode2");
		//Create Gateway node.
		Gateway gateway = new Gateway(new Long(20), "theGatewayName", Split.TYPE_XOR);
		
		//Creating Condition node
		Condition condition1 = new Condition(endNode1, "salaryGreaterThan", "tFormalExpression",
				"http://www.java.com/java",
				"<![CDATA[return  KieFunctions.greaterThan(salaryPercentage, \"2\");]]>");
		Condition condition2 = new Condition(endNode2, "salaryLessEqualThan", "tFormalExpression",
				"http://www.java.com/java",
				"<![CDATA[return  KieFunctions.lessOrEqualThan(salaryPercentage, \"2\");]]>");
		
		
		//Transitions
		Transition startTransition = new Transition(startNode, gateway);
		Transition greaterThanTransition = new Transition(gateway, endNode1, condition1);
		Transition lessEqualTransition = new Transition(gateway, endNode2, condition2);


		//Set transitions to nodes
		startNode.setFirstActivityTransition(startTransition);
		List<Transition> transitions = new ArrayList<Transition>();
		transitions.add(greaterThanTransition);
		transitions.add(lessEqualTransition);
		gateway.setTransitions(transitions);
		
		
		//Process and test
		Process process = new Process("testProcess", startNode, processVariables);
		String marshalledProcess = service.generateBPMN(process);
		System.out.println(marshalledProcess);
	}

}
