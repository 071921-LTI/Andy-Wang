package com.lti.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;
import com.lti.services.AuthServices;
import com.lti.services.AuthServiceImpl;

public class AuthDelegate implements Delegatable {
	
	AuthServices as = new AuthServiceImpl();
	@Override
	public void process(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Retrieve GET, POST, PUT, DELETE...
		String method = rq.getMethod();

		switch (method) {
		case "GET":
			handleGet(rq, rs);
			break;
		case "POST":
			handlePost(rq, rs);
			break;
		case "PUT":
			handlePut(rq, rs);
			break;
		case "DELETE":
			handleDelete(rq, rs);
			break;
		default:
			rs.sendError(405);
		}
	}

	@Override
	public void handleGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = rq.getParameter("username");
		String password = rq.getParameter("password");

		try {
			User user = as.login(username, password);
			if (user != null) {
				String token = user.getId() + ":" + user.getRoleid().getRole();
				rs.setHeader("Authorization", token);
				rs.setStatus(200);
			}else {
				throw new UserNotFoundException();
			}
	
		} catch (UserNotFoundException e) {
			rs.sendError(404);
		}
	}

	@Override
	public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
