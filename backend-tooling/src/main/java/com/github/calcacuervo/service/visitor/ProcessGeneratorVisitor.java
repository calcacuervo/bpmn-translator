package com.github.calcacuervo.service.visitor;

import org.drools.core.process.core.datatype.impl.type.FloatDataType;
import org.drools.core.process.core.datatype.impl.type.ObjectDataType;
import org.drools.core.process.core.datatype.impl.type.StringDataType;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.jbpm.ruleflow.core.factory.SplitFactory;

import com.github.calcacuervo.model.Condition;
import com.github.calcacuervo.model.EndNode;
import com.github.calcacuervo.model.Gateway;
import com.github.calcacuervo.model.Property;
import com.github.calcacuervo.model.StartNode;
import com.github.calcacuervo.model.Task;
import com.github.calcacuervo.model.Transition;
import com.github.calcacuervo.visitor.ProcessComponentVisitor;

public class ProcessGeneratorVisitor implements ProcessComponentVisitor {

	private ExtendedProcessFactory factory;

	public ProcessGeneratorVisitor() {
		super();
		this.factory = ExtendedProcessFactory.createProcess("org.jbpm.process");
		;
	}

	@Override
	public void visit(com.github.calcacuervo.model.Process process) {
		String processName = process.getProcessName();
		factory.name(processName).packageName("com.gitbhut.calcacuervo");

		this.visit(process.getStartNode());
		// factory.startNode(process.getId()).name("Start").done();

		// process variables...
		for (Property property : process.getProperties()) {
			this.visit(property);
		}

		// visit transition to first activity
		this.visit(process.getStartNode().getTransitions().get(0));
	}

	@Override
	public void visit(StartNode startNode) {
		factory.startNode(startNode.getId()).name(startNode.getActivityName()).done();
	}

	@Override
	public void visit(Gateway gateway) {
		// Handle all conditions here.
		SplitFactory splitFactory = factory.splitNode(gateway.getId());
		for (Transition transition : gateway.getTransitions()) {
			Condition condition = transition.getCondition();
			splitFactory.constraint(condition.getTo().getId(), condition.getName(), condition.getType(),
					condition.getLanguageType(), condition.getExpression()).type(gateway.getType());
		}
		splitFactory.done();

		// Visit each transition.
		for (Transition transition : gateway.getTransitions()) {
			transition.accept(this);
		}
	}

	@Override
	public void visit(Property property) {
		// TODO Detect type, support more types. Make this smart.
		if (property.getValue() instanceof String) {
			StringDataType stringDataType = new StringDataType();
			stringDataType.writeValue(property.getValue());
			factory.variable(property.getName(), stringDataType);
		} else if (property.getValue() instanceof Float) {
			FloatDataType floatDataType = new FloatDataType();
			floatDataType.writeValue(property.getValue());
			factory.variable(property.getName(), floatDataType);
		} else {
			ObjectDataType objectDataType = new ObjectDataType();
			objectDataType.writeValue(property.getValue());
			factory.variable(property.getName(), objectDataType);
		}
	}

	@Override
	public void visit(Task task) {
		factory.humanTaskNode(task.getId()).actorId(task.getActorId()).taskName(task.getStepName()).done();

		for (String input : task.getInputs()) {
			factory.humanTaskNode(task.getId()).inMapping(input, input).done();
		}

		for (String output : task.getOutputs()) {
			factory.humanTaskNode(task.getId()).outMapping(output, output).done();
		}

		for (Transition transition : task.getTransitions()) {
			transition.accept(this);
		}
	}

	@Override
	public void visit(Transition transition) {
		transition.getTo().accept(this);
		factory.connection(transition.getFrom().getId(), transition.getTo().getId());
	}

	@Override
	public void visit(Condition condition) {
		// TODO: There is no need to implement this, since is handled in
		// visit(Gateway) method all at same moment. Looks like there is no way
		// to decouple this.
	}

	@Override
	public void visit(EndNode endNode) {
		factory.endNode(endNode.getId()).name(endNode.getActivityName()).done();
	}

	public RuleFlowProcessFactory getFactory() {
		return factory;
	}

}
