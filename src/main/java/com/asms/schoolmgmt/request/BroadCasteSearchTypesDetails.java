package com.asms.schoolmgmt.request;

public class BroadCasteSearchTypesDetails {

	
	
	private boolean allStudents;
	
	private boolean allParents;
	
	private boolean allManagement;
	
	private boolean allNonTeaching;
	
	private boolean allTeachingStaff;
	
	
	
	private String dateOfIssue;
	
	private String subject;

	private String message;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public boolean isAllStudents() {
		return allStudents;
	}
	public void setAllStudents(boolean allStudents) {
		this.allStudents = allStudents;
	}
	public boolean isAllParents() {
		return allParents;
	}
	public void setAllParents(boolean allParents) {
		this.allParents = allParents;
	}
	public boolean isAllManagement() {
		return allManagement;
	}
	public void setAllManagement(boolean allManagement) {
		this.allManagement = allManagement;
	}
	public boolean isAllNonTeaching() {
		return allNonTeaching;
	}
	public void setAllNonTeaching(boolean allNonTeaching) {
		this.allNonTeaching = allNonTeaching;
	}
	
	
	public boolean isAllTeachingStaff() {
		return allTeachingStaff;
	}
	public void setAllTeachingStaff(boolean allTeachingStaff) {
		this.allTeachingStaff = allTeachingStaff;
	}
	
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	
	
	
	
	
}
