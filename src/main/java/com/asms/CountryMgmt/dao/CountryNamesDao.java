package com.asms.CountryMgmt.dao;

import java.util.List;

import com.asms.CountryMgmt.Entity.Country;
import com.asms.CountryMgmt.Entity.StateEntity;
import com.asms.Exception.AsmsException;

/*
 * Interface name : CountryNamesDto
 * This interface contains set of operations to be performed on country table
 */
public interface CountryNamesDao
{
	//Following is the method declaration to get all countries names from DB
   public List<Country> getCountries() throws AsmsException;;

 //Following is the method declaration to get all States names from DB
	public List<StateEntity> getStates() throws AsmsException;
	
	public List<String> getDistrict(int stateId) throws AsmsException;
	
	public List<String> getTehsil(int districtId) throws AsmsException;
	
	public List<String> getVillage(int tehsilId) throws AsmsException;
	
	public List<String> getSubDivision(int districtId) throws AsmsException;
	
}
