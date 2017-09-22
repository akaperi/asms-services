package com.asms.usermgmt.service;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.asms.common.helper.Constants;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.multitenancy.entity.SuperAdmin;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.dao.UserMgmtDao;
import com.asms.usermgmt.entity.StudentType;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;
import com.asms.usermgmt.helper.Validator;
import com.asms.usermgmt.request.ChangePasswordDetails;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.UserRequest;
import com.asms.usermgmt.response.GetUserResponse;
import com.asms.usermgmt.response.LoginResponse;
import com.asms.usermgmt.response.RegistrationResponse;

/*
 * UserMgmtService.java handles user registration, update and delete 
 * functionalities
 */

@Service
@Component
@Path("/user") // this is the entry point of the UserManagement Service
// this is the entry point of the UserManagement Service
public class UserMgmtService extends BaseService {

	@Autowired
	private UserMgmtDao userMgmtDao;

	@Autowired
	private PrivilegesManager privilegesManager;

	@Autowired
	private Validator validator;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UserMgmtService.class);

	@Path("{userId}")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getUser(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@PathParam("userId") String userId, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			if (null == userId || userId.isEmpty()) {
				if (null == userId || userId.isEmpty()) {
					failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
					failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
					return Response.status(200).entity(failureResponse).build();
				}
			}

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {

				UserDetails userObject = userMgmtDao.getUserDetails(userId, tenant);
				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setUser(userObject);
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
	 * api : /user/studentType request type :GET
	 * 
	 * Method : getStudentType -> This method is used for Drop Down. Input:No
	 * input Output: Response object *
	 * 
	 * 
	 */

	@Path("/studentType")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getStudentType(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse) {
		try {

			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			if (null != user) {

				List<StudentType> userObject = userMgmtDao.getAll();

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setStudentTypes(userObject);
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
	 * api : /user/qualificationTypes request type :GET
	 * 
	 * Method : GETqualificationTypes -> This method is used for Drop Down.
	 * Input:No input Output: Response object *
	 * 
	 * 
	 */

	@Path("/qualificationTypes")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getqualificationTypes(@Context HttpServletRequest hRequest,
			@Context HttpServletResponse hResponse) {
		try {

			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			if (null != user) {

				List<QualificationType> userObject = userMgmtDao.getQualification();
				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setQualificationTypes(userObject);
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
	 * api : /user/religionTypes request type :GET
	 * 
	 * Method : GETreligionTypes -> This method is used for Drop Down. Input:No
	 * input Output: Response object *
	 * 
	 * 
	 */

	@Path("/religionTypes")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getreligionTypes(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse) {
		try {

			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			if (null != user) {

				List<ReligionTypes> userObject = userMgmtDao.getReligions();
				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setReligionTypes(userObject);
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
	 * api : /user/casteTypes request type :GET
	 * 
	 * Method : GETcasteTypes -> This method is used for Drop Down. Input:No
	 * input Output: Response object *
	 * 
	 * 
	 */

	@Path("/casteTypes")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getcastTypes(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse) {
		try {

			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			if (null != user) {

				List<CasteTypes> userObject = userMgmtDao.getCasteName();

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setCasteTypes(userObject);
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

	// ----------------------------------------------------------
	// Update code goes here
	// ----------------------------------------------------------
	@Path("/update")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response update(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			// validator.validateRequest(userRequest, messages, "update");
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.admin_category_userManagement,
					Constants.privileges.update_check.toString());
			if (pUser.isPrivileged()) {
				UserDetails userDetails = userRequest.getUserDetails();
				userDetails.setRole(userRequest.getUserRole());
				userDetails.setSubRole(userRequest.getSubRole());
				userMgmtDao.updateUser(userDetails, user, tenant);
				rReponse.setMessage("Updated Successfully");
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
	// end of Update
	// method------------------------------------------------------------------------------

	/*
	 * api : /user/updateStudent request type :POST
	 * 
	 * Method : updateStudent -> This method is used to the StudentDetails in
	 * the Database. Input:UserRequest Output: Response object *
	 * 
	 * 
	 */

	/*
	 * @Path("/updateStudent")
	 * 
	 * @POST
	 * 
	 * @Consumes("application/json")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") public Response
	 * updateStudent(@Context HttpServletRequest hRequest, @Context
	 * HttpServletResponse hResponse, UserRequest userRequest) {
	 * RegistrationResponse rReponse = new RegistrationResponse();
	 * ResourceBundle messages; try { // get bundles for error messages messages
	 * = AsmsHelper.getMessageFromBundle(); // validate request
	 * //validator.validateRequest(userRequest, messages, "update"); // validate
	 * user details //validator.validateUserDetails(userRequest, messages);
	 * HttpSession session = hRequest.getSession(); User user = (User)
	 * session.getAttribute("ap_user"); // authorize
	 * 
	 * // check if logged in user has got rights to create user PrincipalUser
	 * pUser = privilegesManager.isPrivileged(user, userRequest.getUserRole(),
	 * userRequest.getRequestType()); if (pUser.isPrivileged()) { UserDetails
	 * userDetails = userRequest.getUserDetails();
	 * userDetails.setRole(userRequest.getUserRole());
	 * userDetails.setSubRole(userRequest.getSubRole());
	 * userMgmtDao.updateStudentLoad(userDetails, user);
	 * rReponse.setMessage("Updated Successfully"); return
	 * Response.status(Status.OK).entity(rReponse).build();
	 * 
	 * } else { FailureResponse failureResponse = new FailureResponse();
	 * failureResponse.setCode(Integer.parseInt(messages.getString(
	 * "NOT_AUTHORIZED_CODE")));
	 * failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"))
	 * ; return Response.status(200).entity(failureResponse).build(); }
	 * 
	 * } catch (AsmsException ex) { // construct failure response
	 * FailureResponse failureResponse = new FailureResponse(ex); return
	 * Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build(
	 * ); } }
	 */

	/*
	 * api : /user/search request type :GET
	 * 
	 * Method : search -> This method is used the Entry point for Search API.
	 * Input:role/admissionNo/firstName/LastName Output: Response object *
	 * 
	 * 
	 */

	@Path("/search")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response search(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("role") String role, @QueryParam("admissionNo") String admissionNo,
			@QueryParam("firstName") String studentFirstName, @QueryParam("lastName") String studentLastName,
			@QueryParam("firstName") String mngmtFirstName, @QueryParam("lastName") String mngmtLastName,
			@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName,
			@QueryParam("tenantId") String tenant) {
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if (admissionNo == null && studentFirstName == null && studentLastName == null && mngmtFirstName == null
					&& mngmtLastName == null && firstName == null && lastName == null) {
				failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
				return Response.status(200).entity(failureResponse).build();

			}

			List<UserDetails> list = userMgmtDao.search(role, admissionNo, studentFirstName, studentLastName,
					mngmtFirstName, mngmtLastName, firstName, lastName, tenant);
			GetUserResponse getUserResponse = new GetUserResponse();
			getUserResponse.setUserDetails(list);

			return Response.status(Status.OK).entity(getUserResponse).build();
		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}

	@Path("/register")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response register(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			validator.validateRequest(userRequest, messages, "create");
			// validate user details
			validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.admin_category_userManagement,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
				UserDetails userDetails = userRequest.getUserDetails();
				userDetails.setRole(userRequest.getUserRole());
				userDetails.setSubRole(userRequest.getSubRole());
				String userid = userMgmtDao.registerUser(userDetails, user, tenant);
				rReponse.setIsNew("true");
				rReponse.setUserId(userid);
				rReponse.setProgressPercentage(20);
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

	// -------------------------------------------
	/*
	 * api : /user/register/details request type :POST
	 * 
	 * Method : registerAdditionalDetails -> This method does the creation of
	 * details object. Input : UserRequest object outbut: Response object *
	 * 
	 * 
	 */
	@Path("/register/details")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response registerAdditionalDetails(@Context HttpServletRequest hRequest,
			@Context HttpServletResponse hResponse, UserRequest userRequest, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		try {
			// get bundles for error messages
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			// validate request
			validator.validateRequest(userRequest, messages, "detail");
			// validate user details
			validator.validateAdditionalUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if loggedin user has got rights to create user
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.admin_category_userManagement,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
				UserDetails userDetails = userRequest.getUserDetails();
				userDetails.setRole(userRequest.getUserRole());
				userMgmtDao.addDetails(userRequest.getUserDetails(), user, tenant);
				rReponse.setProgressPercentage(20);
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

	/*
	 * api : /user/login request type :POST
	 * 
	 * Method : login -> This method does login authentication input :
	 * UserRequest object outbut: Response object
	 * 
	 * 
	 */

	@Path("/login")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response login(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("id") String id, UserDetails userDetails) {
		FailureResponse failureResponse = new FailureResponse();
		// get the error code and description from resource bundles
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		try {

			LoginResponse loginResponse = new LoginResponse();
			if (null == id || id.isEmpty()) {
				failureResponse.setCode(Integer.parseInt(messages.getString("ID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("ID_NULL"));
			}

			if (null == userDetails) {

				failureResponse.setCode(Integer.parseInt(messages.getString("REQUEST_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("REQUEST_NULL"));

				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			} else {
				boolean result = userMgmtDao.authenticate(hRequest, hResponse, id, userDetails.getEmail(),
						userDetails.getUserPassword());
				if (result) {
					HttpSession session = hRequest.getSession();
					Object user = session.getAttribute("ap_user");
					if (user instanceof SuperAdmin) {
						loginResponse.setRole("Super_Admin");
					}
					if (user instanceof User) {
						loginResponse.setRole(((User) user).getRoleObject().getRoleName());
					}

					return Response.status(Status.OK).entity(loginResponse).build();
				} else {
					failureResponse.setCode(Integer.parseInt(messages.getString("AUTHENTICATION_FAILED_CODE")));
					failureResponse.setErrorDescription(messages.getString("AUTHENTICATION_FAILED_MSG"));
					return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
				}

			}
		} catch (AsmsException ex) {
			// construct failure response
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}

	}

	@Path("/changepassword")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response changepassword(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			ChangePasswordDetails changePasswordDetails, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate ChangePasswordDetails
			validator.validateChangePasswordDetails(changePasswordDetails, messages);
			// validate user details
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize
			if (null != user) {
				userMgmtDao.changePassword(changePasswordDetails, user, tenant);
				rReponse.setProgressPercentage(20);
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
