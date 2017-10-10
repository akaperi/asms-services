/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.service;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import com.asms.Exception.ExceptionHandler;
import com.asms.attendance.dao.AttendanceDao;

import com.asms.common.helper.AsmsHelper;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.UserRequest;
import com.asms.usermgmt.request.student.StudentDetails;
import com.asms.usermgmt.response.GetUserResponse;



@Service
@Component
@Path("/attendance")
public class AttendanceMgmtService extends BaseService {
	
	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private ExceptionHandler exceptionHandler;
	
	@Autowired
	private PrivilegesManager privilegesManager;
	
	private static final Logger logger = LoggerFactory.getLogger(AttendanceMgmtService.class);

	@Path("studentAttendance")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response addStudentAttendance(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant) {
		
		
		
		
				return null;
	}
	
	
	
	@Path("staffAttendance")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response addStaffAttendance(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant) {
				return null;
	}
	
	
	@Path("/student/search")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getStudents(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("className") String className, @QueryParam("sectionName") String sectionName,
			@QueryParam("tenantId") String tenant) {
		try {

			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			if (null != user) {
				
				if(className == null || className.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("CLASSNAME_NULL_CODE"),
							messages.getString("CLASSNAME_NULL_MSG"));
				}
				if(sectionName == null || sectionName.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("SECTION_NULL_CODE"),
							messages.getString("SECTION_NULL_MSG"));
				}
				
				List<UserDetails> list = attendanceDao.search(className, sectionName, tenant);

				

				//List<StudentType> userObject = userMgmtDao.getAll();

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setUserDetails(list);
				return Response.status(Status.OK).entity(getUserResponse.getStudentDetails()).build();
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
