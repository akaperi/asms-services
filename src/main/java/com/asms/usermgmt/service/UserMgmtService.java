package com.asms.usermgmt.service;

import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.adminmgmt.entity.Admin;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.dao.UserMgmtDao;
import com.asms.usermgmt.helper.PrincipalUser;
import com.asms.usermgmt.helper.Validator;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.UserRequest;

/*
 * UserMgmtService.java handles user registration, update and delete 
 * functionalities
 */

@Service
@Component
@Path("/user")

public class UserMgmtService extends BaseService {

	@Autowired
	private UserMgmtDao userMgmtDao;

	@Autowired
	private PrivilegesManager privilegesManager;

	@Autowired
	private Validator validator;

	private static final Logger logger = LoggerFactory.getLogger(UserMgmtService.class);

	/*
	 * api : /user/register request type :POST
	 * 
	 * Method : register -> This method does the creation of user object. Input :
	 * UserRequest object outbut: Response object *
	 * 
	 * 
	 */

	@Path("/register")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response register(UserRequest userRequest) {
		try {
			// validate request
			validator.validateRequest(userRequest);
			// authorize
			
			//check if logged in user has got rights to create user
			PrincipalUser user = privilegesManager.isPrivileged(userRequest.getLoggedInUserEmail(), userRequest.getUserRole(),
					userRequest.getRequestType());
			if(user.isPrivileged()) {
				UserDetails userDetails = userRequest.getUserDetails();
				userDetails.setRole(userRequest.getUserRole());
				userMgmtDao.registerUser(userDetails,user.getLoggedInUser());
				
			}else {
				//  throw authorization error
			}
			
			

			return Response.status(200).entity("success").build();
		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}

	@Path("/login")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response login(Admin admin) throws Exception {
		Response response = null;
		try {

			if (null == admin) {
				FailureResponse failureResponse = new FailureResponse();
				// get the error code and description from resource bundles
				ResourceBundle messages = AsmsHelper.getMessageFromBundle();
				failureResponse.setCode(Integer.parseInt(messages.getString("SYSTEM_EXCEPTION_CODE")));
				failureResponse.setErrorDescription(messages.getString("SYSTEM_EXCEPTION"));

				response = Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			} else {

				response = Response.status(200).entity("S").build();

			}
		} catch (Exception e) {
			logger.error("error", e);
			// TODO: handle exception
		}
		return response;
	}

}
