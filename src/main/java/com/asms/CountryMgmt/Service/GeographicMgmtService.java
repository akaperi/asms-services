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

import com.asms.CountryMgmt.Entity.StateEntity;
import com.asms.CountryMgmt.Response.GeographicSuccessResponse;
import com.asms.CountryMgmt.dao.GeographicDao;
import com.asms.Exception.AsmsException;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.response.FailureResponse;

/*
 * GeographicMgmtService.java is the service class for GeographicMgmt module.
 * This class has all the Geographic management REST api end points.
 * 
 * The root path for GeographicMgmtService  api is /geo
 */
@Service
@Component
@Path("/states")
public class GeographicMgmtService 
{
	@Autowired
	GeographicDao geographicDao;
	
	ArrayList<StateEntity> stateEntity;

	// @Path("/country")
	@GET
	// Data Exchange format : Following service method receives data in the form
	// of JSON
	@Consumes("application/json")
	// Data Exchange format : Following service method gives data in the form of
	// JSON
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatesByCountry() throws AsmsException
	{
		Response response;
		stateEntity = new ArrayList<>();
		AsmsException asmsException=new AsmsException();
		try
		{
			stateEntity =  (ArrayList<StateEntity>) geographicDao.getStateByCountry();
			GeographicSuccessResponse geographicSuccessResponse = new GeographicSuccessResponse();
			geographicSuccessResponse.setCountryNames(stateEntity);
			response = Response.status(200).entity(geographicSuccessResponse.getCountryNames()).build();
		}
		catch(Exception e) {
	//		e.printStackTrace();
			FailureResponse failureResponse = new FailureResponse();
			//get the error code and description from resource bundles
			ResourceBundle messages =AsmsHelper.getMessageFromBundle();
			
			asmsException.setCode(messages.getString("STATE_NAMES_NOT_FOUND_CODE"));
			asmsException.setDescription(messages.getString("STATE_NAMES_NOT_FOUND_MSG"));
			
			response= Response.status(Status.NO_CONTENT).entity(failureResponse).build();
		
		}
		return response;
	}
	/*ArrayList<GeograhicEntity> countryList;
	
	//@Path("/country")
	@GET
	// Data Exchange format : Following service method receives data in the form
	// of JSON
	@Consumes("application/json")
	// Data Exchange format : Following service method gives data in the form of
	// JSON
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAll() throws AsmsException
	{
		Response response;
		//countryList = new ArrayList<>();
		AsmsException asmsException=new AsmsException();

		try
		{
			countryList =  geographicDao.getAll();
				
			GeographicSuccessResponse geographicSuccessResponse = new GeographicSuccessResponse();
			 //CountrySuccessResponse countrySuccessResponse = new CountrySuccessResponse();
			geographicSuccessResponse.setCountryNames(countryList);
			 response = Response.status(200).entity(geographicSuccessResponse).build();
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
	
	}*/
		
		

}
