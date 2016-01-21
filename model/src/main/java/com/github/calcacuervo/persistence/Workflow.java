package com.github.calcacuervo.persistence;

import java.util.Date;

public class Workflow {

	private String workflowName;

	private Date effectiveDate;

	private Boolean publish;

	private String version;

	private com.github.calcacuervo.model.Process process;

	public Workflow() {
		super();
	}

	public Workflow(String workflowName, com.github.calcacuervo.model.Process process) {
		super();
		this.workflowName = workflowName;
		this.effectiveDate = new Date();
		this.publish = Boolean.FALSE;
		this.version = "1";
		this.process = process;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Boolean getPublish() {
		return publish;
	}

	public void setPublish(Boolean publish) {
		this.publish = publish;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public com.github.calcacuervo.model.Process getProcess() {
		return process;
	}

	public void setProcess(com.github.calcacuervo.model.Process process) {
		this.process = process;
	}

}
