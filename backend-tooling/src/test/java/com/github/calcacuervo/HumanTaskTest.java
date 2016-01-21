package com.github.calcacuervo;

import java.util.ArrayList;
import java.util.Arrays;
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
public class HumanTaskTest {

	@Test
	public void processVariablesTest() {
		BPMNGenerationService service = new BPMNGenerationServiceImpl();

		List<Property> processVariables = new ArrayList<Property>();

		// Create nodes
		StartNode startNode = new StartNode(new Long(1));
		EndNode endNode = new EndNode(new Long(5), "EndNode1");
		Task firstActivity = new Task(new Long(2), "firstTask");
		firstActivity.setActorId("demian");
		firstActivity.setInputs(new ArrayList<String>(Arrays.asList("inputParameter1")));
		firstActivity.setOutputs(new ArrayList<String>(Arrays.asList("outputParameter1")));
		
		//Transitions
		Transition firstActivityTransition = new Transition(startNode, firstActivity);
		Transition taskToEndNodeTransition = new Transition(firstActivity, endNode);
		
		//Set transitions to nodes
		startNode.setFirstActivityTransition(firstActivityTransition);
		firstActivity.setNextTransition(taskToEndNodeTransition);
		
		//Process and test
		Process process = new Process("testProcess", startNode, processVariables);
		String marshalledProcess = service.generateBPMN(process);
		System.out.println(marshalledProcess);
	}

}
