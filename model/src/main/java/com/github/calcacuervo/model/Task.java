package com.github.calcacuervo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.github.calcacuervo.visitor.ProcessComponentVisitor;

/**
 * Represents a human step to be performed in the process.
 * 
 * @author calcacuervo
 * 
 */
@Entity(name = "TASK")
public class Task extends Activity {

	@OneToOne
	private Transition nextTransition;

	/**
	 * The username of the actor id.
	 */
	@Column(name = "ACTOR_ID")
	private String actorId;

	/**
	 * This is the name of the group id.
	 */
	@Column(name = "GROUP_ID")
	private String groupId;

	/**
	 * The name of this human task.
	 */
	@Column(name = "STEP_NAME")
	private String stepName;

	/**
	 * The list of inputs of this task.
	 */
	@Column(name = "INPUTS")
	private List<String> inputs;

	/**
	 * The list of outputs of this task.
	 */
	private List<String> outputs;

	// TODO add assignments!

	/**
	 * Constructor.
	 * 
	 * @param stepName
	 */
	public Task(Long id, String stepName, Transition nextStep) {
		super(id, stepName);
		this.nextTransition = nextStep;
		inputs = new ArrayList<String>();
		outputs = new ArrayList<String>();
	}

	/**
	 * Constructor.
	 * 
	 * @param stepName
	 */
	public Task(Long id, String stepName) {
		super(id, stepName);
		inputs = new ArrayList<String>();
		outputs = new ArrayList<String>();
	}

	@Override
	public List<Transition> getTransitions() {
		List<Transition> onlyOneNext = new ArrayList<Transition>();
		if (this.nextTransition != null) {
			onlyOneNext.add(this.nextTransition);
		}
		return onlyOneNext;
	}

	public String getActorId() {
		return actorId;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setInputs(List<String> inputs) {
		this.inputs = inputs;
	}

	public List<String> getInputs() {
		return inputs;
	}

	public void setOutputs(List<String> outputs) {
		this.outputs = outputs;
	}

	public List<String> getOutputs() {
		return outputs;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public void setNextTransition(Transition nextTransition) {
		this.nextTransition = nextTransition;
	}

	@Override
	public void accept(ProcessComponentVisitor visitor) {
		visitor.visit(this);
	}

}
