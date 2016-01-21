package com.github.calcacuervo.service;

import com.github.calcacuervo.model.Activity;
import com.github.calcacuervo.model.Condition;

public interface ToolingUIService {
	
	Condition getCondition(Long id, String name, String type, String field,
			String operator, String value, Activity success, Activity otherwise);
	
	

}
