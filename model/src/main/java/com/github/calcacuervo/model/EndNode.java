package com.github.calcacuervo.model;

import java.util.List;

import com.github.calcacuervo.visitor.ProcessComponentVisitor;

/**
 * Represents a human step to be performed in the process.
 * 
 * @author calcacuervo
 * 
 */
public class EndNode extends Activity {
	
	public EndNode() {
		super();
	}

	public EndNode(Long id, String theActivityName) {
		super(id, theActivityName);
	}
	
	@Override
	public void accept(ProcessComponentVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public List<Transition> getTransitions() {
		return null;
	}

}
