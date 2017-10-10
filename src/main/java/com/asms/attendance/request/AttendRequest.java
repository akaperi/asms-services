/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.request;

public class AttendRequest {
	
	
	// request type can be create/update/delete
		private String requestType;

		// user role can be student/management/ teaching staff/ non teaching staff
		private String userRole;

		// subrole
		private String subRole;
		
		private AttendanceDetails attendanceDetails;

}
