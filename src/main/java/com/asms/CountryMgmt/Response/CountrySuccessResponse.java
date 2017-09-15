package com.asms.CountryMgmt.Response;

import java.util.ArrayList;
import java.util.List;

import com.asms.CountryMgmt.Entity.Country;
import com.asms.CountryMgmt.Entity.StateEntity;
import com.asms.common.response.SuccessResponse;

/*
 * Class name: CountrySuccessResponse to return list of countries
 */
public class CountrySuccessResponse extends SuccessResponse {
	
	private ArrayList<Country> countries;
	
	
	private List<StateEntity> stateEntities;
	
	private List<Country> countries2;
	
	private List<String> districtNames,tehsilNames,villageNames,subDivisionNames;

	public ArrayList<Country> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<Country> countryList) {
		this.countries = countryList;
	}

	public List<Country> getCountries2() {
		return countries2;
	}

	public void setCountries2(List<Country> countries2) {
		this.countries2 = countries2;
	}

	public List<StateEntity> getStateEntities() {
		return stateEntities;
	}

	public void setStateEntities(List<StateEntity> stateEntities) {
		this.stateEntities = stateEntities;
	}

	public List<String> getDistrictNames() {
		return districtNames;
	}

	public void setDistrictNames(List<String> districtNames) {
		this.districtNames = districtNames;
	}

	public List<String> getTehsilNames() {
		return tehsilNames;
	}

	public void setTehsilNames(List<String> tehsilNames) {
		this.tehsilNames = tehsilNames;
	}

	public List<String> getVillageNames() {
		return villageNames;
	}

	public void setVillageNames(List<String> villageNames) {
		this.villageNames = villageNames;
	}

	public List<String> getSubDivisionNames() {
		return subDivisionNames;
	}

	public void setSubDivisionNames(List<String> subDivisionNames) {
		this.subDivisionNames = subDivisionNames;
	}

	

}
