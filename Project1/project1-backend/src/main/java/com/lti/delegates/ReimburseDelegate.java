package com.lti.delegates;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.ReimburseStatus;
import com.lti.models.Reimbursement;
import com.lti.services.AuthServiceImpl;
import com.lti.services.AuthServices;
import com.lti.services.ReimburseService;
import com.lti.services.ReimburseServiceImpl;
import com.lti.services.UserService;
import com.lti.services.UserServiceImpl;

public class ReimburseDelegate implements Delegatable{
	UserService us = new UserServiceImpl();
	AuthServices as = new AuthServiceImpl();
	ReimburseService rbs = new ReimburseServiceImpl();
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
		}
		
	}

	@Override
	public void handleGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// String passed through the request if any
		String pathNext = (String) rq.getAttribute("pathNext");

		if (pathNext != null) {
			try {
				Reimbursement reimburse= rbs.getReimburseById(Integer.valueOf(pathNext));
				try (PrintWriter pw = rs.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(reimburse));
				}
			} catch (NumberFormatException e) {
				List<Reimbursement> reimburses = rbs.GetReimbursesByStatus(pathNext);
				try (PrintWriter pw = rs.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(reimburses));
				}
			}
		} else {
			String authToken = rq.getHeader("Authorization");
			
			try {
				if(authToken == null || !as.authorize(authToken)) {
					rs.sendError(403);
				}else {
					/*
					 * for /users endpoint, returning all users
					 */
					List<Reimbursement> reimburses= rbs.GetAllReimbursements();
					try (PrintWriter pw = rs.getWriter()) {
						pw.write(new ObjectMapper().writeValueAsString(reimburses));
					}
				}
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		InputStream request = rq.getInputStream();
		Reimbursement reimburse = new ObjectMapper().readValue(request, Reimbursement.class);
		
		if (!rbs.UpdateReimbursement(reimburse)) {
			rs.sendError(400, "Unable to update Reimbursement.");
		} else {
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(reimburse));
			}
			rs.setStatus(201);
		}


		
	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		InputStream request = rq.getInputStream();
		// Converts the request body into a User.class object
		Reimbursement reimburse = new ObjectMapper().readValue(request, Reimbursement.class);
		
		if (!rbs.addReimburse(reimburse)) {
			rs.sendError(400, "Unable to add Reimbursement.");
		} else {
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(reimburse));
			}
			rs.setStatus(201);
		}

		
	}

	@Override
	public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		System.out.println("In handleDelete");
		InputStream request = rq.getInputStream();
		// Converts the request body into a User.class object
		Reimbursement reimburse = new ObjectMapper().readValue(request, Reimbursement.class);
		
		if (!rbs.RemoveReimburse(reimburse)) {
			rs.sendError(400, "Unable to delete Reimbursement.");
		} else {
			try (PrintWriter pw = rs.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(reimburse));
			}
			rs.setStatus(201);
		}

		
	}

}
