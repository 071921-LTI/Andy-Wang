package com.lti.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;
import com.lti.services.AuthServiceImpl;
import com.lti.services.AuthServices;
import com.lti.services.UserService;
import com.lti.services.UserServiceImpl;

public class UserDelegate implements Delegatable {

	UserService us = new UserServiceImpl();
	AuthServices as = new AuthServiceImpl();
	private static Logger log = LogManager.getRootLogger();
	
	@Override
	public void process(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
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
			log.error("unable to process request");
		}

	}

	@Override
	public void handleGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("In handleGet");
		/*
		 * TODO: - if a path param is provided( /users/{id}) return user by an id -
		 * return 404 status code if not found - if no path param is provided( /users )
		 * return all users
		 */

		// String passed through the request if any
		String pathNext = (String) rq.getAttribute("pathNext");

		if (pathNext != null) {
			User user = null;
			try {
				user = us.getUserById(Integer.valueOf(pathNext));
				try (PrintWriter pw = rs.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(user));
				}
			} catch (NumberFormatException e) {
				try {
					user = us.getUserByUsername(pathNext);
				} catch (UserNotFoundException e1) {
					rs.sendError(404);
				}
				try (PrintWriter pw = rs.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(user));
				}
				
			} catch (UserNotFoundException e) {
				log.error("Exception was thrown: " + e.fillInStackTrace());
				rs.sendError(404);
				
			}
		} else {
			String authToken = rq.getHeader("Authorization");
			
			try {
				if(authToken == null || !as.authorizeManager(authToken)) {
					rs.sendError(403);
				}else {
					/*
					 * for /users endpoint, returning all users
					 */
					List<User> users = us.getUsers();
					try (PrintWriter pw = rs.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(users));
					}
				}
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				log.error("Exception was thrown: " + e.fillInStackTrace());
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				log.error("Exception was thrown: " + e.fillInStackTrace());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("Exception was thrown: " + e.fillInStackTrace());
			}
		}
	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("In handlePut");
		BufferedReader request = rq.getReader();
		// Converts the request body into a User.class object
		User user = new ObjectMapper().readValue(request, User.class);
		System.out.println(user);
		if (!us.updateUser(user)) {
			rs.sendError(400, "Unable to update user.");
		} else {
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(user));
			}
			rs.setStatus(201);
		}

	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("In handlePost");

		/*
		 * TODO: - create a user based on JSON data provided in the body 
		 * 	- return 400 if	unable to 
		 * 	- return 201 if successful
		 */
		
		BufferedReader request = rq.getReader();
		// Converts the request body into a User.class object
		User user = new ObjectMapper().readValue(request, User.class);
		System.out.println(user);
		if (!us.addUser(user)) {
			rs.sendError(400, "Unable to add user.");
		} else {
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(user));
			}
			rs.setStatus(201);
		}

	}

	@Override
	public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("In handleDelete");
		String pathNext = (String) rq.getAttribute("pathNext");
		User user = null;
		try {
			user = us.getUserById(Integer.valueOf(pathNext));
			us.deleteUser(user);
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(user));
			}
		} catch (NumberFormatException e) {
			try {
				user = us.getUserByUsername(pathNext);
				us.deleteUser(user);
			} catch (UserNotFoundException e1) {
				rs.sendError(404);
			}
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(user));
			}
			
		} catch (UserNotFoundException e) {
			rs.sendError(404);
		}
	}

}
