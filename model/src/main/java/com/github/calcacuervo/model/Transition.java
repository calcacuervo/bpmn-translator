package com.github.calcacuervo.model;

import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;

import com.github.calcacuervo.visitor.ProcessComponent;
import com.github.calcacuervo.visitor.ProcessComponentVisitor;

/**
 * This class represents a transition from one step to another.
 * 
 * @author calcacuervo
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = As.PROPERTY, property = "@class")
public class Transition implements ProcessComponent {

	/**
	 * Which destination activity.
	 */
	private Activity to;

	@JsonIgnore
	private Activity from;

	/**
	 * The condition of the activity. It can be null, so most of the times there
	 * is no condition associated to a transition.
	 */
	@OneToOne
	private Condition condition;

	public Transition() {
		super();
	}

	/**
	 * Constructor with all the fields.
	 * 
	 * @param from
	 * @param to
	 * @param condition
	 */
	public Transition(Activity from, Activity to, Condition condition) {
		this.from = from;
		this.to = to;
		this.condition = condition;
	}

	/**
	 * Constructor with all the fields except for the condition.
	 * 
	 * @param from
	 * @param to
	 */
	public Transition(Activity from, Activity to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public void accept(ProcessComponentVisitor visitor) {
		visitor.visit(this);
	}

	public Activity getTo() {
		return to;
	}

	public void setTo(Activity to) {
		this.to = to;
	}

	public Activity getFrom() {
		return from;
	}

	public void setFrom(Activity from) {
		this.from = from;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

}
