package com.github.calcacuervo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.calcacuervo.model.EndNode;
import com.github.calcacuervo.model.Process;
import com.github.calcacuervo.model.Property;
import com.github.calcacuervo.model.StartNode;
import com.github.calcacuervo.model.Task;
import com.github.calcacuervo.model.Transition;
import com.github.calcacuervo.service.BPMNGenerationService;
import com.github.calcacuervo.service.impl.BPMNGenerationServiceImpl;

/**
 * Unit test for simple App.
 */
public class ProcessVariablesTest {

	@Test
	public void processVariablesTest() {
		BPMNGenerationService service = new BPMNGenerationServiceImpl();

		//Process Variables
		List<Property> processVariables = new ArrayList<Property>();
		processVariables.add(new Property(new String("robben"), "arjen"));
		processVariables.add(new Property(new Float(22.2), "doubleValue"));

		// Create nodes
		StartNode startNode = new StartNode(new Long(1));
		EndNode endNode = new EndNode(new Long(5), "EndNode1");
		Task firstActivity = new Task(new Long(2), "firstTask");
		firstActivity.setActorId("demian");
		
		//Transitions
		Transition firstActivityTransition = new Transition(startNode, firstActivity);
		Transition transition1 = new Transition(firstActivity, endNode);
		
		//Set transitions to nodes
		startNode.setFirstActivityTransition(firstActivityTransition);
		firstActivity.setNextTransition(transition1);
		
		//Process and test
		Process process = new Process("testProcess", startNode, processVariables);
		String marshalledProcess = service.generateBPMN(process);
		System.out.println(marshalledProcess);
	}

}
