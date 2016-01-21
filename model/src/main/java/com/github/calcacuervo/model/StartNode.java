package com.github.calcacuervo.model;

import java.util.ArrayList;
import java.util.List;

import com.github.calcacuervo.visitor.ProcessComponentVisitor;

/**
 * Represents a human step to be performed in the process.
 * 
 * @author calcacuervo
 * 
 */
public class StartNode extends Activity {

	private Transition firstActivityTransition;

	public StartNode() {
		super();
	}

	public StartNode(String theActivityName, Long id) {
		super(id, theActivityName);
	}

	/**
	 * Constructor.
	 * 
	 * @param stepName
	 */
	public StartNode(Long id) {
		super(id, "StartNode");
	}

	@Override
	public void accept(ProcessComponentVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public List<Transition> getTransitions() {
		List<Transition> onlyOneNext = new ArrayList<Transition>();
		if (this.firstActivityTransition != null) {
			onlyOneNext.add(this.firstActivityTransition);
		}
		return onlyOneNext;

	}

	public Transition getFirstActivityTransition() {
		return firstActivityTransition;
	}

	public void setFirstActivityTransition(Transition firstActivityTransition) {
		this.firstActivityTransition = firstActivityTransition;
	}

}
