package com.asms.reportsGeneration.dao;



import com.asms.Exception.AsmsException;
import com.asms.reportsGeneration.request.CurriculamDetails;
import com.asms.usermgmt.entity.User;

public interface ReportsGenDao {
	
	public void getCurriculamReport(CurriculamDetails curriculamDetails, User user, String tenant) throws AsmsException;;
	public void getAllUsers(String year, String tenant ) throws AsmsException;
	
	public void getAdmissionDetails(String year,String domain)  throws AsmsException;

	public void getAdmissionEnquiryDetails(String s, String d)  throws AsmsException;
	public void getApplicationStatusDetails(String s, String d)  throws AsmsException;
	
	public void getStudentsDetail(String s, String c, String s1,String d)  throws AsmsException;
	public void getNewAdmissions(String year,String domain)  throws AsmsException;

}
