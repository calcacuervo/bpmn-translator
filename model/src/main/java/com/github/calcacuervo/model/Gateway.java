package com.github.calcacuervo.model;

import java.util.List;

import com.github.calcacuervo.visitor.ProcessComponentVisitor;

public class Gateway extends Activity {

	private Integer type;

	private List<Transition> transitions;

	public Gateway() {
		super();
	}

	public Gateway(Long id, String theActivityName) {
		super(id, theActivityName);
	}

	public Gateway(Long id, String theActivityName, Integer type) {
		super(id, theActivityName);
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	@Override
	public void accept(ProcessComponentVisitor visitor) {
		visitor.visit(this);
	}

}
