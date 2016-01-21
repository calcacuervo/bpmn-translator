package com.github.calcacuervo.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;

import com.github.calcacuervo.visitor.ProcessComponent;
import com.github.calcacuervo.visitor.ProcessComponentVisitor;

/**
 * This class represents a process, with the possibility of navigating it.
 * 
 * @author calcacuervo
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = As.PROPERTY, property = "@class")
public class Process implements ProcessComponent {

	/**
	 * The name of the process.
	 */
	private String processName;

	private StartNode startNode;

	private List<Property> properties;

	public Process() {
		super();
	}

	@JsonCreator
	public Process(@JsonProperty("processName") String processName, @JsonProperty("startNode") StartNode startNode,
			@JsonProperty("properties") List<Property> properties) {
		super();
		this.processName = processName;
		this.startNode = startNode;
		this.properties = properties;
	}

	@Override
	public void accept(ProcessComponentVisitor visitor) {
		visitor.visit(this);
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public StartNode getStartNode() {
		return startNode;
	}

	public void setStartNode(StartNode startNode) {
		this.startNode = startNode;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

}
