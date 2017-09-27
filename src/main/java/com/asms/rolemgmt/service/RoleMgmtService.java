package com.asms.rolemgmt.service;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
import com.asms.common.response.SuccessResponse;
import com.asms.common.service.BaseService;
import com.asms.rolemgmt.dao.RoleMgmtDao;
import com.asms.rolemgmt.dao.RoleMgmtDaoImpl;
import com.asms.schoolmgmt.request.UserRequest;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.Validator;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.service.UserMgmtService;


/*
 * RoleMgmtService.java handles role registration, update and delete 
 * functionalities
 */

@Service
@Component
@Path("/role") 
//this is the entry point of the RoleManagement Service
public class RoleMgmtService extends BaseService {
	
	@Autowired
	private RoleMgmtDao roleMgmtDao;
	
	@Autowired
	private PrivilegesManager privilegesManager;

	@Autowired
	private Validator validator;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RoleMgmtService.class);
	
	
	
	/*
	 * api : /role/subrole/create request type :POST
	 * 
	 * Method : createSubRole -> This method is used to create subRoles. Input:roleName,subRoleName
	 * input Output: Response object *
	 * 
	 * 
	 */
	
	@Path("/subrole/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createSubRole(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant , @QueryParam("roleName") String roleName,@QueryParam("subRoleName") String subRoleName) 
	{
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		
		try {
		FailureResponse failureResponse = new FailureResponse();
		SuccessResponse sResponse = new SuccessResponse();
		
		HttpSession session = hRequest.getSession();
		User user = (User) session.getAttribute("ap_user");
		
		if (null != user) {
			
			roleMgmtDao.insertSubRole(roleName, subRoleName, tenant);
			return Response.status(Status.OK).entity(sResponse).build();
		} else {
			
			failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
			failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
			return Response.status(200).entity(failureResponse).build();
		}

	} catch (AsmsException ex) {
		// construct failure response
		FailureResponse failureResponse = new FailureResponse(ex);
		return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
	}
		
	}
	

}
