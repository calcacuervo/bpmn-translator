package com.github.calcacuervo.visitor;

import com.github.calcacuervo.visitor.ProcessComponentVisitor;

public interface ProcessComponent {
	
	void accept(ProcessComponentVisitor visitor);

}
