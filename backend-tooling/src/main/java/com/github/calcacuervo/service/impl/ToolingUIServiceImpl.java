package com.github.calcacuervo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.calcacuervo.model.Activity;
import com.github.calcacuervo.model.Condition;
import com.github.calcacuervo.service.ToolingUIService;

public class ToolingUIServiceImpl implements ToolingUIService {

	/**
	 * this service is to create a translate a tooling user simple condition
	 * like salary > 2 into a JBPM condition which is a a condition with
	 * constraints, like constraint1: salary > 2 XOR constraint2: salary <= 2
	 */
	@Override
	public Condition getCondition(Long id, String name, String type,
			String field, String operator, String value, Activity success, Activity otherwise) {
		//TODO Make this smart ;)
		
		Condition constraint1 = new Condition(success, name, type, "java", field + operator + value);
		String complementaryOperator = "<";
		if (operator.equals("<")) {
			complementaryOperator = ">=";
		}
		Condition constraint2 = new Condition(otherwise, name, type, "java", field + complementaryOperator + value);
		List<Condition> constraints = new ArrayList<Condition>();
		constraints.add(constraint1);
		constraints.add(constraint2);
//		Condition condition = new Condition(id, field, null, null, constraints);
//		condition.setName(name);
//		condition.setType(type);
//		return condition;
		return null;
	}

}
