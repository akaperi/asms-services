package com.asms.reportsGeneration.service;
/*
 * ReportsGenerationService.java manages report generation(gives reports regarding admission enqry report,
 * curriculam report fee mgmt report etc.. 
 * 
 */

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.reportsGeneration.dao.ReportsGenDao;
import com.asms.reportsGeneration.helper.ReportGenValidator;
import com.asms.reportsGeneration.request.CurriculamDetails;
import com.asms.reportsGeneration.request.RequestForReports;
import com.asms.schoolmgmt.helper.ValidateAcademicYear;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.response.RegistrationResponse;

@Service
@Component
@Path("/reports")
// this is the entry point of the ReportsGeneration Service class
public class ReportsGenerationService extends BaseService {

	@Autowired
	private ReportsGenDao reportsGenDao;

	// @Autowired
	// private PrivilegesManager privilegesManager;

	@Autowired
	private ReportGenValidator reportGenValidator;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Path("/curriculamplan")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response register(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			RequestForReports requestForReports, @QueryParam("domain") String domain) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;

		CurriculamDetails curriculamDetails = null;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			/*
			 * PrincipalUser pUser = privilegesManager.isPrivileged(user,
			 * Constants.admin_category_userManagement,
			 * Constants.privileges.create_check.toString()); if (pUser.isPrivileged()) {
			 */
			if (user != null) {
				curriculamDetails = requestForReports.getCurriculamDetails();

				reportGenValidator.validateRequest(curriculamDetails, messages);

				reportsGenDao.getCurriculamReport(curriculamDetails, user, domain);

				return Response.status(Status.OK).entity(rReponse).build();

			} else {
				FailureResponse failureResponse = new FailureResponse();
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

	@Path("/allusers")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response listOfStaffMembers(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("year") String year, @QueryParam("domain") String domain) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			
			if (user != null) {
				if (null == year || year.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("year_not_valid_null_code"),
							messages.getString("year_not_valid_null_msg"));
				}

				if (!(ValidateAcademicYear.validateAcademicYear(year))) {
					throw exceptionHandler.constructAsmsException(messages.getString("Invalid_year_null_code"),
							messages.getString("Invalid_year_null_msg"));
				}

				reportsGenDao.getAllUsers(year, domain);
				
				return Response.status(Status.OK).entity(rReponse).build();

			} else {
				FailureResponse failureResponse = new FailureResponse();
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

	
	
	
	
	//Admission Reports----------------------
	
	
	@Path("/admission")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response admissionReport(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("year") String year, @QueryParam("domain") String domain) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			
			if (user != null) {
				if (null == year || year.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("year_not_valid_null_code"),
							messages.getString("year_not_valid_null_msg"));
				}

				/*if (!(ValidateAcademicYear.validateAcademicYear(year))) {
					throw exceptionHandler.constructAsmsException(messages.getString("Invalid_year_null_code"),
							messages.getString("Invalid_year_null_msg"));
				}*/

				reportsGenDao.getAdmissionDetails(year, domain);
				
				return Response.status(Status.OK).entity(rReponse).build();

			} else {
				FailureResponse failureResponse = new FailureResponse();
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
	
	
	@Path("/admissionEnquiry")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response admissionEnquiry(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("year") String year, @QueryParam("domain") String domain) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			
			if (user != null) {
				if (null == year || year.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("year_not_valid_null_code"),
							messages.getString("year_not_valid_null_msg"));
				}

				/*if (!(ValidateAcademicYear.validateAcademicYear(year))) {
					throw exceptionHandler.constructAsmsException(messages.getString("Invalid_year_null_code"),
							messages.getString("Invalid_year_null_msg"));
				}*/

				reportsGenDao.getAdmissionEnquiryDetails(year, domain);
				
				return Response.status(Status.OK).entity(rReponse).build();

			} else {
				FailureResponse failureResponse = new FailureResponse();
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
	
	
	@Path("/applicationStatus")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response applicationStatus(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("year") String year, @QueryParam("domain") String domain) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			
			if (user != null) {
				if (null == year || year.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("year_not_valid_null_code"),
							messages.getString("year_not_valid_null_msg"));
				}

				/*if (!(ValidateAcademicYear.validateAcademicYear(year))) {
					throw exceptionHandler.constructAsmsException(messages.getString("Invalid_year_null_code"),
							messages.getString("Invalid_year_null_msg"));
				}*/

				reportsGenDao.getApplicationStatusDetails(year, domain);
				
				return Response.status(Status.OK).entity(rReponse).build();

			} else {
				FailureResponse failureResponse = new FailureResponse();
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
	
	
	//class wise student report
	@Path("/students")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response studentsReport(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("year") String year, @QueryParam("domain") String domain) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			
			if (user != null) {
				if (null == year || year.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("year_not_valid_null_code"),
							messages.getString("year_not_valid_null_msg"));
				}

				/*if (!(ValidateAcademicYear.validateAcademicYear(year))) {
					throw exceptionHandler.constructAsmsException(messages.getString("Invalid_year_null_code"),
							messages.getString("Invalid_year_null_msg"));
				}*/

				reportsGenDao.getNewAdmissions(year,domain);
				
				return Response.status(Status.OK).entity(rReponse).build();

			} else {
				FailureResponse failureResponse = new FailureResponse();
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
	//New Admission report
	
	
	
//	newAdmissions
	@Path("/newAdmissions")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response newAdmissions(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("year") String year, @QueryParam("class") String Class, @QueryParam("section") String section,@QueryParam("domain") String domain) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			
			if (user != null) {
				if (null == year || year.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("year_not_valid_null_code"),
							messages.getString("year_not_valid_null_msg"));
				}

				/*if (!(ValidateAcademicYear.validateAcademicYear(year))) {
					throw exceptionHandler.constructAsmsException(messages.getString("Invalid_year_null_code"),
							messages.getString("Invalid_year_null_msg"));
				}*/

				reportsGenDao.getStudentsDetail(year, Class, section ,domain);
				
				return Response.status(Status.OK).entity(rReponse).build();

			} else {
				FailureResponse failureResponse = new FailureResponse();
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
