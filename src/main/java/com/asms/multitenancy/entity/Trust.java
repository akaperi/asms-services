/**
@{author} gayithri.hg
04-Sep-2017
*/
package com.asms.multitenancy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity 
@Table(name = "trust_details")
public class Trust {
	/**
	@{author} gayithri.hg
	04-Sep-2017
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "trust_id")
	private String trustId;
	
	@Column(name = "trust_name")
	private String name;
	
	@Column(name = "trust_address")
	private String address;
	
	@Column(name = "trust_contact_no")
	private long contactNo;
	
	
	@Column(name = "trust_email")
	private String email;
	
	@Column(name = "trust_affiliation_id")
	private String affiliationId;
	
	@Column(name = "trust_gps_latitude")
	private BigDecimal latitude;
	
	@Column(name = "trust_gps_longitude")
	private BigDecimal longitude;
	
	@Column(name = "trust_city")
	private String city;
	
	@Column(name = "trust_state")
	private String state;
	
	@Column(name = "trust_country")
	private String country;
	
	
	
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAffiliationId() {
		return affiliationId;
	}

	public void setAffiliationId(String affiliationId) {
		this.affiliationId = affiliationId;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getTrustId() {
		return trustId;
	}

	public void setTrustId(String trustId) {
		this.trustId = trustId;
	}

	
}
