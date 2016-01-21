package com.github.calcacuervo.visitor;

import com.github.calcacuervo.model.Condition;
import com.github.calcacuervo.model.EndNode;
import com.github.calcacuervo.model.Gateway;
import com.github.calcacuervo.model.Process;
import com.github.calcacuervo.model.Property;
import com.github.calcacuervo.model.StartNode;
import com.github.calcacuervo.model.Task;
import com.github.calcacuervo.model.Transition;

public interface ProcessComponentVisitor {

	public void visit(Process process);

	public void visit(StartNode startNode);

	public void visit(Gateway gateway);

	public void visit(Property property);

	public void visit(Task task);

	public void visit(Transition transition);

	public void visit(Condition condition);

	public void visit(EndNode endNode);

}
