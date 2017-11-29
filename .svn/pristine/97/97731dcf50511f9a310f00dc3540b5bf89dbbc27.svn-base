package com.asms.feemgmt.dao;

import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.feemgmt.entity.FeeCategory;
import com.asms.feemgmt.entity.FeeMaster;
import com.asms.feemgmt.entity.PaymentMode;
import com.asms.feemgmt.request.FeeCategoryDetails;
import com.asms.feemgmt.request.FeeMasterDetails;

public interface FeeMgmtDao {
	
	public List<FeeCategory> getFeeCategory(String domain) throws AsmsException; 	
	
	public void createDefaultFee(String schema) throws AsmsException;
	
	public void insertFee(FeeCategoryDetails categoryDetails ,String domain) throws AsmsException;
	
	public void setupFee(FeeMasterDetails feeMasterDetails , String domain) throws AsmsException;
	
	public List<FeeMaster> viewSetupFee(String domain) throws AsmsException;
	
	public void createDefaultPaymentMode(String schema) throws AsmsException;
	
	
	public List<PaymentMode> getPaymentMode(String domain) throws AsmsException;
	
	

}
