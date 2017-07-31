package com.asms.usermgmt.student.dao;




import com.asms.Exception.AsmsException;
import com.asms.usermgmt.student.entity.Student;

public interface StudentMgmtDao {
	
	public void register(Student student) throws AsmsException;

}
