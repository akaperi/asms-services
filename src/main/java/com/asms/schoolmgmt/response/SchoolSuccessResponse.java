package com.asms.schoolmgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.schoolmgmt.entity.Section;

public class SchoolSuccessResponse extends SuccessResponse{
	
	private List<String> subjectNames,classNames;
	
	private List<Section> sections;

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

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

}
