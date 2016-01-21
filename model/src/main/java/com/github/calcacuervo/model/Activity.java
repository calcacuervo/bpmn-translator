package com.github.calcacuervo.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;

import com.github.calcacuervo.visitor.ProcessComponent;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
@JsonSubTypes({ @Type(value = EndNode.class, name = "endNode"), @Type(value = Gateway.class, name = "gateway"),
		@Type(value = StartNode.class, name = "startNode"), @Type(value = Task.class, name = "task") })
public abstract class Activity implements ProcessComponent {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("activityName")
	private String activityName;

	public Activity() {
		super();
	}

	public Activity(Long id, String theActivityName) {
		this.activityName = theActivityName;
		this.id = id;
	}

	public abstract List<Transition> getTransitions();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

}
