package com.asms.CountryMgmt.Response;

import java.util.ArrayList;

import com.asms.CountryMgmt.Entity.Country;
import com.asms.common.response.SuccessResponse;

/*
 * Class name: CountrySuccessResponse to return list of countries
 */
public class CountrySuccessResponse extends SuccessResponse {
	
	private ArrayList<Country> countries;

	public ArrayList<Country> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<Country> countryList) {
		this.countries = countryList;
	}

}
