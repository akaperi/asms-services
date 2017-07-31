package com.asms.adminmgmt.service;

import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.MessageLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.ExceptionHandler;
import com.asms.adminmgmt.dao.AdminMgmtDao;
import com.asms.adminmgmt.entity.Admin;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;




@Service
@Component
@Path("/admin")


public class AdminMgmtService extends BaseService{
	
	@Autowired
	private AdminMgmtDao adminMgmtDao;
	private ExceptionHandler exceptionHandler;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AdminMgmtService.class);

	
	
	
	/* api : /admin/create
	 * request type :POST
	 * 
	 * Method : createAdmin -> This method does the creation of admin object.
	 * Input : Admin object
	 * outbut: Response object * 
	 * 
	 * 
	 */
	
	@Path("/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createAdmin(Admin admin) {
		try {
			
			logger.debug("hi1");
			
            
            adminMgmtDao.createAdmin(admin);
            return Response.status(200).entity("success").build();
		} catch (Exception ex) {
			
			return Response.status(200).entity("failed").build();
		} 
	}
	

	
	
	
	@Path("/login")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response login(Admin admin) throws Exception {
		Response response=null;
		try
		{
	
			if(null == admin)
			{
				FailureResponse failureResponse = new FailureResponse();
				//get the error code and description from resource bundles
				ResourceBundle messages =AsmsHelper.getMessageFromBundle();
				failureResponse.setCode(Integer.parseInt(messages.getString("SYSTEM_EXCEPTION_CODE")));
				failureResponse.setErrorDescription(messages.getString("SYSTEM_EXCEPTION"));
				
				response= Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}
			else{
				
				
				response= Response.status(200).entity("S").build();
				
			}
		}
		catch (Exception e) {
			logger.error("error",e);
			// TODO: handle exception
		}
       return response;
	}
	

}
