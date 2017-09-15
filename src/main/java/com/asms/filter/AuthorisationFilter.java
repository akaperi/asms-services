package com.asms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.asms.common.service.AuthenticationService;

public class AuthorisationFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AuthorisationFilter.class);

	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpSession session = httpServletRequest.getSession();

			// keeping session id in MDC so that it can be taken and used for login purpose
			MDC.put("sessionId", session.getId());

			// get authenrication header
			String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);
			String pathInfo = httpServletRequest.getPathInfo();
			// skip login flow for authorization header check as only after login requests
			// are checked for authorization header
			if (pathInfo.contains("login")) {
				chain.doFilter(request, response);
			} else {
				AuthenticationService authenticationService = new AuthenticationService();
				Object user = session.getAttribute("ap_user");
				boolean authenticationStatus = authenticationService.authenticate(authCredentials, user);
				if (authenticationStatus) {
					chain.doFilter(request, response);
				} else {
					if (response instanceof HttpServletResponse) {
						HttpServletResponse httpServletResponse = (HttpServletResponse) response;
						httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					}
				}
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
