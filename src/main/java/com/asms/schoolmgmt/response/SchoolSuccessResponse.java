package com.asms.schoolmgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;

public class SchoolSuccessResponse extends SuccessResponse{
	
	private List<String> subjectNames,classNames;

	public List<String> getSubjectNames() {
		return subjectNames;
	}

	public void setSubjectNames(List<String> subjectNames) {
		this.subjectNames = subjectNames;
	}

	public List<String> getClassNames() {
		return classNames;
	}

	public void setClassNames(List<String> classNames) {
		this.classNames = classNames;
	}

}
