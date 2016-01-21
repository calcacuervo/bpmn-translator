package com.github.calcacuervo.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.github.calcacuervo.visitor.ProcessComponent;
import com.github.calcacuervo.visitor.ProcessComponentVisitor;

/**
 * This class represents Process properties. For JBPM process:
 * <bpmn2:itemDefinition id="_salaryPercentageItem" structureRef="Float"/>
 * <bpmn2:property id="salaryPercentage"
 * itemSubjectRef="_salaryPercentageItem"/> For the model is a "Field".
 * 
 * @author demi
 * 
 */
@Entity(name = "PROPERTY")
public class Property implements ProcessComponent {

	@Column(name = "VALUE")
	private Object value;

	@Column(name = "NAME")
	private String name;

	public Property() {
		super();
	}

	public Property(Object value, String name) {
		super();
		this.value = value;
		this.name = name;
	}

	@Override
	public void accept(ProcessComponentVisitor visitor) {
		visitor.visit(this);
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
