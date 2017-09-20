package com.asms.schoolmgmt.request;

public class BroadCasteSearchTypesDetails {

	private boolean allStudents;
	
	private boolean allParents;
	
	private boolean allManagement;
	
	private boolean allNonTeaching;
	
	private boolean allTeachingStaff;
	
	private String allclass;
	
	private String allSection;
	
	private String subject;

	private String message;
	
	private String fromEmail;
	
	
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
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
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
	public String getAllclass() {
		return allclass;
	}
	public void setAllclass(String allclass) {
		this.allclass = allclass;
	}
	public String getAllSection() {
		return allSection;
	}
	public void setAllSection(String allSection) {
		this.allSection = allSection;
	}
	
	
	
	
	
}
