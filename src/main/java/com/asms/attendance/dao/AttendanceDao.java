/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.dao;

import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.student.StudentDetails;

public interface AttendanceDao {
	
	public List<UserDetails> search(String className, String sectionName, String tenant) throws AsmsException;
	

}
