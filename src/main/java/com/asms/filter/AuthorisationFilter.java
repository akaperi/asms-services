package com.asms.filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class AuthorisationFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AuthorisationFilter.class);

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();

		//logger.info("Session ID {}", session.getId());
		//logger.info("Customer information {}", session.getAttribute("customer"));
		MDC.put("sessionId", session.getId());
		String url = httpServletRequest.getRequestURI().toString();
		String requestType=httpServletRequest.getMethod();
		//logger.debug("url: " + url);
		String userid = "";

		String authCode = "";
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = httpServletRequest.getHeader(key);
			if (key.equalsIgnoreCase("x-parse-application-id")) {
				authCode = value;
				logger.debug("authcode {} session id {}", authCode, session.getId());
				break;
			}
		}
		
		//String pathInfo = httpServletRequest.getPathInfo(); // /{value}/test
		//String[] pathParts = pathInfo.split("/");
       
		
		
		// HttpServletResponse httpServletResponse = (HttpServletResponse)
		// response;

		// httpServletResponse.addHeader("Access-Control-Expose-Headers",
		// "Set-Cookie");
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
