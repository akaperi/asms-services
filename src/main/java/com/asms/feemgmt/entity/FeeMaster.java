package com.asms.feemgmt.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : FeeMaster
 * 
 * FeeMaster class is the Mapping entity class for fee_master table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "fee_master")
public class FeeMaster {
	
	/**
	@{author} Devendra Singh
	03-11-2017
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name="category")
	private String feeCategory;
	

	@Column(name="payment_type")
	private String paymentType;
	
	@Column(name="no_of_installments")
	private int noOfInstallments;
	
	
	@Column(name="no_of_months")
	private int noOfMonths;


	public int getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}


	



	public int getNoOfInstallments() {
		return noOfInstallments;
	}


	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}


	public int getNoOfMonths() {
		return noOfMonths;
	}


	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}


	public String getFeeCategory() {
		return feeCategory;
	}


	public void setFeeCategory(String feeCategory) {
		this.feeCategory = feeCategory;
	}


	public String getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}




	

}
