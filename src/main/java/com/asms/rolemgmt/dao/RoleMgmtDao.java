package com.asms.rolemgmt.dao;
import com.asms.Exception.AsmsException;

public interface RoleMgmtDao {
	

	/**
	 * @param Role
	 * @return
	 * @throws AsmsException
	 */
	public void createDefaultRoles(String schema) throws AsmsException;
	
}
