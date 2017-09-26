package com.asms.rolemgmt.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.rolemgmt.dao.RoleMgmtDao;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.asms.rolemgmt.helper.RoleValidator;
import com.asms.rolemgmt.response.GetRoleResponse;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.Validator;
import com.asms.usermgmt.response.GetUserResponse;
import com.asms.usermgmt.service.UserMgmtService;

@Service
@Component
@Path("/roles") // this is the entry point of the RoleMgmtService 
// this is the entry point of the RoleMgmtService

public class RoleMgmtService extends BaseService{
	
	@Autowired
	private RoleMgmtDao roleMgmtDao;
	
	@Autowired
	private RoleValidator validator;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserMgmtService.class);
	
	
	
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getRole(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse, @QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {
				List<Role> roles = roleMgmtDao.getRole(tenant);
				GetRoleResponse getRoleResponse = new GetRoleResponse();
				getRoleResponse.setRoles(roles);
				return Response.status(Status.OK).entity(getRoleResponse).build();

			} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}

		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}

	}
	
	@Path("/subroles")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSubRole(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("role") String role, @QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			if(null == role){
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}
			
			if (null != user) {
				//String Role = null;
				List<SubRole> subroles = roleMgmtDao.getSubRole(role, tenant);
				GetRoleResponse getRoleResponse = new GetRoleResponse();
				getRoleResponse.setSubRoles(subroles);
				return Response.status(Status.OK).entity(getRoleResponse).build();

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
