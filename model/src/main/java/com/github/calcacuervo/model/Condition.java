package com.github.calcacuervo.model;

import com.github.calcacuervo.visitor.ProcessComponent;
import com.github.calcacuervo.visitor.ProcessComponentVisitor;

/**
 * <bpmn2:conditionExpression xsi:type="bpmn2:tFormalExpression"
 * id="_uiPwUQuuEeSCeOio4TBp6Q"
 * language="http://www.java.com/java"><![CDATA[return
 * KieFunctions.greaterThan(salaryPercentage,
 * "2");]]></bpmn2:conditionExpression>
 * 
 * @author mfangio
 * 
 */
public class Condition implements ProcessComponent {

	private Activity to;

	private String name;

	private String type;

	private String languageType;

	private String expression;

	public Condition() {
		super();
	}

	public Condition(Activity to, String name, String type, String languageType, String expression) {
		super();
		this.to = to;
		this.name = name;
		this.type = type;
		this.languageType = languageType;
		this.expression = expression;
	}

	public Activity getTo() {
		return to;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getLanguageType() {
		return languageType;
	}

	public String getExpression() {
		return expression;
	}

	@Override
	public void accept(ProcessComponentVisitor visitor) {
		visitor.visit(this);
	}

}
