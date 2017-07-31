package com.asms.CountryMgmt.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Entity Class: GeograhicEntity
 * Attributes : siNo, countryName, stateName, districtName, subDivision, talukName, villageName.
 * This class is the Hibernate mapping entity class for geographic_details table in DB
 * 
 */
@Entity
@Table(name = "geographic_details")
public class GeograhicEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="S.No.")
	private int siNo;
	@Column(name= "country")
	private String countryName;
	@Column(name= "State")
	private String stateName;
	@Column(name= "District")
	private String districtName;
	@Column(name= "SubDivision")
	private String subDivision;
	@Column(name= "Tehsil")
	private String talukName;
	@Column(name= "Village")
	private String villageName;
	
	public int getSiNo() {
		return siNo;
	}
	public void setSiNo(int siNo) {
		this.siNo = siNo;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}
	public String getTalukName() {
		return talukName;
	}
	public void setTalukName(String talukName) {
		this.talukName = talukName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	

}
