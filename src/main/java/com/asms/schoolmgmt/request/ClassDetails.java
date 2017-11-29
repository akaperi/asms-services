/**
@{author} mallikarjun.guranna
14-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.List;

public class ClassDetails {
	/**
	 * @{author} mallikarjun.guranna 14-Sep-2017
	 */

	
	
	private String name;
	private List<String> subjects;
	
	private List<String> sections;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public List<String> getSections() {
		return sections;
	}

	public void setSections(List<String> sections) {
		this.sections = sections;
	}
	
	

	



}
