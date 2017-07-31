package com.asms.CountryMgmt.dao;

import java.util.ArrayList;

import com.asms.CountryMgmt.Entity.Country;

/*
 * Interface name : CountryNamesDto
 * This interface contains set of operations to be performed on country table
 */
public interface CountryNamesDao
{
	//Following is the method declaration to get all countries names from DB
public ArrayList<Country> getCountries();

	
}
