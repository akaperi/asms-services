package com.asms.multitenancy.service;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.multitenancy.entity.Trust;
import com.asms.multitenancy.helper.TrustValidator;
import com.asms.multitenancy.request.TrustDetails;
import com.asms.schoolmgmt.request.UserRequest;
import com.asms.usermgmt.response.GetUserResponse;
import com.asms.usermgmt.response.RegistrationResponse;


@Service
@Component
@Path("/common") 
public class MultitenancyService extends BaseService{
	
	@Autowired
	private MultitenancyDao multitenancyDao;

	@Autowired
	private TrustValidator trustValidator;
	
	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MultitenancyService.class);
	
	
	/*
	 * api : /common/trust/register  Request type :POST
	 * 
	 * Method :register -> This method is used to insert Trust Details. Input:UserRequest
	 * input Output: Response object *
	 * 
	 * 
	 */
	@Path("/trust/register")
	@POST 
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response register(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest)
	{
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			trustValidator.validateTrustDetails(userRequest, messages, "create");
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			
			
			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");
			
	
			
			
			if(user !=null){
			TrustDetails trust = userRequest.getTrustDetails();
			
			multitenancyDao.createTrust(trust,dbProperties.getProperty("default_schema"));
			
			return Response.status(Status.OK).entity(rReponse).build();
			}
			
			else{
				return Response.status(Status.EXPECTATION_FAILED).entity(rReponse).build();
			
			}
		}catch(AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		
	
		
		}
	
	}
	
	/*
	 * api : /common/trusts  Request type :GET
	 * 
	 * Method :register -> This method is used to get Trust Details. Input:No
	 * input Output: Response object *
	 * 
	 * 
	 */
	@Path("/trusts")
	@GET 
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getTrusts(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse)
	{
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");

			if (null != user) {
				List<Trust> trusts = multitenancyDao.getTrust(dbProperties.getProperty("default_schema"));

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setTrusts(trusts);
				return Response.status(Status.OK).entity(getUserResponse).build();

			} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}

		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}
}
