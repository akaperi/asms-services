package com.asms.schoolmgmt.service;

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
import com.asms.common.service.BaseService;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.multitenancy.entity.SuperAdmin;
import com.asms.schoolmgmt.dao.SchoolMgmtDao;
import com.asms.schoolmgmt.entity.Class;
import com.asms.schoolmgmt.entity.SetupSchoolDetails;
import com.asms.schoolmgmt.helper.SchoolValidator;
import com.asms.schoolmgmt.request.BroadCasteSearchTypesDetails;
import com.asms.schoolmgmt.request.SchoolDetails;
import com.asms.schoolmgmt.request.UserRequest;
import com.asms.schoolmgmt.response.SchoolSuccessResponse;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
<<<<<<< HEAD
import com.asms.usermgmt.request.student.ParentDetails;
=======
import com.asms.usermgmt.helper.PrincipalUser;
>>>>>>> refs/heads/master
import com.asms.usermgmt.response.GetUserResponse;
import com.asms.usermgmt.response.RegistrationResponse;


/*
 * SchoolMgmtService.java handles school registration, update , get and delete 
 * functionalities
 */

@Service
@Component
@Path("/school")
public class SchoolMgmtService extends BaseService {

	@Autowired
	private SchoolMgmtDao schoolMgmtDao;

	@Autowired
	private PrivilegesManager privilegesManager;

	@Autowired
	private SchoolValidator schoolValidator;

	@Autowired
	private MultitenancyDao multitenancyDao;
	
	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	private static final Logger logger = LoggerFactory.getLogger(SchoolMgmtService.class);


	/*
	 * api : /school/names request type :GET
	 * 
	 * Method : getNamesOfBoard -> This method is used for Drop Down. Input:No
	 * input Output: Response object *
	 * 
	 * 
	 */

	@Path("/names")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getNamesOfBoard(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {
				List<String> boardObject = schoolMgmtDao.getNames();

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setBoardNames(boardObject);
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

	// school Registration goes here
	// ----------------------------------------

	@Path("/register")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response register(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			schoolValidator.validateSchoolDetailsRequest(userRequest, messages, "create");
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");
			if (user instanceof SuperAdmin) {

				// create schema
				String schema = multitenancyDao.createTenantId(userRequest.getSchoolDetails().getId(),
						userRequest.getSchoolDetails().getName());
				boolean result = multitenancyDao.createSchema(schema);
				if (result) {
					SchoolDetails school = userRequest.getSchoolDetails();
					schoolMgmtDao.createSchool(school,schema);
					return Response.status(Status.OK).entity(rReponse).build();
				}else{
					FailureResponse failureResponse = new FailureResponse();
					failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
					failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
					return Response.status(200).entity(failureResponse).build();
				}
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
	
	
	//setup school code goes here
	//------------------------------------
	@Path("/setup")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response setupSchool(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			schoolValidator.validateSetupSchoolRequest(userRequest, messages, "create");
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");
			String schema = null ;
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User)user, userRequest.getUserRole(),
					userRequest.getRequestType());
			if (pUser.isPrivileged()) {
					SetupSchoolDetails setupSchoolDetails = userRequest.getSetupSchoolDetails();
					schoolMgmtDao.setupSchool(setupSchoolDetails,schema);
					return Response.status(Status.OK).entity(rReponse).build();
				}else{
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

	
	/*
	 * api : /school/classes request type :GET
	 * 
	 * Method : getNamesOfBoard -> This method is used for Drop Down. Input:tenantId
	 * input Output: Response object *
	 * 
	 * 
	 */
	@Path("/classes")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getClass(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse, @QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {

				List<Class> class1 = schoolMgmtDao.getClasses(tenant);

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setClass1(class1);
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

	/*
	 * api : /school/subjects request type :GET
	 * 
	 * Method : getSubjects -> This method is used for Drop Down. Input:boardId
	 * input Output: Response object *
	 * 
	 * 
	 */
	@Path("/subjects")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSubjects(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("id") int boardId) {
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if (boardId == 0) {
				failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
				return Response.status(200).entity(failureResponse).build();

			}

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (user != null) {
				List<String> list = schoolMgmtDao.getSujects(boardId);
				SchoolSuccessResponse countrySuccessResponse = new SchoolSuccessResponse();
				countrySuccessResponse.setSubjectNames(list);
				return Response.status(Status.OK).entity(countrySuccessResponse).build();

			} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}
		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}

	
	/*
	 * api : /school/classSubjects request type :GET
	 * 
	 * Method : getClassSubjectsByClassId -> This method is used for Drop Down. Input:classId
	 * input Output: Response object *
	 * 
	 * 
	 */
	@Path("/classSubjects")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getClassSubjectsByClassId(@Context HttpServletRequest hRequest,
			@Context HttpServletResponse hResponse, @QueryParam("classId") int classId,@QueryParam("tenantId") String tenant) {
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if (classId == 0) {
				failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
				return Response.status(200).entity(failureResponse).build();

			}

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (user != null) {
				List<String> list = schoolMgmtDao.getClassSujects(classId,tenant);
				SchoolSuccessResponse countrySuccessResponse = new SchoolSuccessResponse();
				countrySuccessResponse.setClassNames(list);
				return Response.status(Status.OK).entity(countrySuccessResponse).build();

			} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}
		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}
	
	@Path("/broadCasteMessages")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getbroadCasteMessages(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,@QueryParam("tenantId") 
	String tenant,@QueryParam("parentDetails") boolean parent,@QueryParam("managementDetails") boolean management,@QueryParam("studentDetails") boolean student) 
	{	try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {
				
			
				
				List<BroadCasteSearchTypesDetails> braodCasteMessages =schoolMgmtDao.get(tenant,parent,management,student);

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setBroadCasteSearchTypesDetails(braodCasteMessages);
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