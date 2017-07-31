package com.asms.CountryMgmt.Response;

import java.util.ArrayList;

import com.asms.CountryMgmt.Entity.GeograhicEntity;
import com.asms.CountryMgmt.Entity.StateEntity;
import com.asms.common.response.SuccessResponse;

/* Class name : GeographicSuccessResponse to return list of
 * countryNames
 * stateName
 * districtName
 * subDivision
 * talukName
 * villageName
 */

public class GeographicSuccessResponse extends SuccessResponse {
	
	private ArrayList<StateEntity> countryNames;
	private ArrayList<GeograhicEntity> stateName;
	private ArrayList<GeograhicEntity> districtName;
	private ArrayList<GeograhicEntity> subDivision;
	private ArrayList<GeograhicEntity> talukName;
	private ArrayList<GeograhicEntity> villageName;
	
	public ArrayList<StateEntity> getCountryNames() {
		return countryNames;
	}
	public void setCountryNames(ArrayList<StateEntity> stateEntity) {
		this.countryNames =  stateEntity;
	}
	public ArrayList<GeograhicEntity> getStateName() {
		return stateName;
	}
	public void setStateName(ArrayList<GeograhicEntity> stateName) {
		this.stateName = stateName;
	}
	public ArrayList<GeograhicEntity> getDistrictName() {
		return districtName;
	}
	public void setDistrictName(ArrayList<GeograhicEntity> districtName) {
		this.districtName = districtName;
	}
	public ArrayList<GeograhicEntity> getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(ArrayList<GeograhicEntity> subDivision) {
		this.subDivision = subDivision;
	}
	public ArrayList<GeograhicEntity> getTalukName() {
		return talukName;
	}
	public void setTalukName(ArrayList<GeograhicEntity> talukName) {
		this.talukName = talukName;
	}
	public ArrayList<GeograhicEntity> getVillageName() {
		return villageName;
	}
	public void setVillageName(ArrayList<GeograhicEntity> villageName) {
		this.villageName = villageName;
	}
	
}
