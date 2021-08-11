package com.lti.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lti.delegates.PokemonDelegate;
import com.lti.delegates.UserDelegate;

public class RequestHelper {

	private UserDelegate ud = new UserDelegate();
	private PokemonDelegate pd = new PokemonDelegate();
	
	public void process(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
		String path = rq.getPathInfo();
		
		// if no path is found sent an error
		if (path != null) {
			path = path.substring(1);
			
			/*
			 * if a "/" character is present in path, split at that character and returns the first element
			 * 	- path = "users"
			 * 	- setting a request attribute to the second part of the url: 1
			 */
			if(path.indexOf("/") != -1) {
				String[] paths = path.split("/");
				path = paths[0];
				rq.setAttribute("pathNext", paths[1]);
			}
			
			switch(path) {
			case "users":
				ud.process(rq, rs);
				break;
			case "pokemons":
				// Can add auth behavior here.
				pd.process(rq, rs);
				break;
			case "auth":
				// TODO: create auth delegate 
			default:
				rs.sendError(400, "Path not supported:" + path);
			}
		} else {
			rs.sendError(400, "No path found.");
		}
	}
}
