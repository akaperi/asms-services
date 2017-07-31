package com.asms.CountryMgmt.dao;

import java.util.ArrayList;

import com.asms.CountryMgmt.Entity.StateEntity;


public interface GeographicDao {
	
	//public ArrayList<GeograhicEntity> getAll();
	public ArrayList<StateEntity>  getCountries();
	public ArrayList<StateEntity>  getStateByCountry();
	public ArrayList<?>  getDistrictByState();
	public ArrayList<?>  getSubDivisionBySistrict();
	public ArrayList<?>  getTalukBySubDivision();
	public ArrayList<?>  getVillageByTaluk();

}
