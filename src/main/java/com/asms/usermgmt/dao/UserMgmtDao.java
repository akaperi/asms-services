package com.asms.usermgmt.dao;

import com.asms.Exception.AsmsException;
import com.asms.usermgmt.entity.User;

public interface UserMgmtDao {
	
	public void register(User user);
	
	public String getUserRole(String email) throws AsmsException;
	
	

}
