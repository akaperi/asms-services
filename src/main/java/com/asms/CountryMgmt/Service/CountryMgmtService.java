package com.asms.CountryMgmt.Service;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.CountryMgmt.Entity.Country;
import com.asms.CountryMgmt.Response.CountrySuccessResponse;
import com.asms.CountryMgmt.dao.CountryNamesDao;
import com.asms.Exception.AsmsException;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.response.FailureResponse;

/* Service class name: CountryMgmtService 
 * purpose : To manage country names
 * This class is the Web service end point.
 * The root path for CountryMgmtService is  API is /countries
 */

@Service
@Component
@Path("/countries")
public class CountryMgmtService {
	@Autowired
	CountryNamesDao countryNamesDao;
	ArrayList<Country> countryList;

	// @Path("/country")
	@GET
	// Data Exchange format : Following service method receives data in the form
	// of JSON
	@Consumes("application/json")
	// Data Exchange format : Following service method gives data in the form of
	// JSON
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getCountriesName() throws AsmsException
	{
		Response response;
		countryList = new ArrayList<>();
		AsmsException asmsException=new AsmsException();
		
			try
			{
				countryList = countryNamesDao.getCountries();
					
				 CountrySuccessResponse countrySuccessResponse = new CountrySuccessResponse();
				 countrySuccessResponse.setCountries(countryList);
				 response = Response.status(200).entity(countrySuccessResponse.getCountries()).build();
			}
			catch(Exception e) {
				
		//		e.printStackTrace();

				FailureResponse failureResponse = new FailureResponse();
				//get the error code and description from resource bundles
				ResourceBundle messages =AsmsHelper.getMessageFromBundle();
				
				asmsException.setCode(messages.getString("COUNTRY_NAMES_NOT_FOUND_CODE"));
				asmsException.setDescription(messages.getString("COUNTRY_NAMES_NOT_FOUND_MSG"));
				
				response= Response.status(Status.NO_CONTENT).entity(failureResponse).build();
			
			}
		
		
		return response;
	}

}
