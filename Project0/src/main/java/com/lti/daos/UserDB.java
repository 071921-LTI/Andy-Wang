package com.lti.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lti.exceptions.UserInvalidException;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;
import com.lti.util.ConnectionUtil;

public class UserDB implements UserDao{
//will access the database by using queries
	private String user;
	private String name;
	private String pass;
	private String id;
	private static Logger log = LogManager.getRootLogger();
	public UserDB (String file) {
		this.user = file;
		this.name = this.user + "_name";
		this.pass = this.user + "_password";
		this.id = this.user + "_id";
	}
	
	public User getUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User resUser = null;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select * from project0." + this.user+ " where " + this.name + " = ?";
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1,username); //1 refers to first ? to parameterize
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) { 
				String userName = rs.getString(this.name);
				String password = rs.getString(this.pass);
				int id = rs.getInt(this.id);
				resUser = new User(userName,password);
				resUser.setId(id);
				return resUser;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		
		throw new UserNotFoundException();
	}

	@Override
	public boolean addUser(User loguser) {                           
		// TODO Auto-generated method stub
		String sql = "insert into project0." + this.user + "(" + this.name + "," + this.pass + ") values (?,?) returning " + id;
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loguser.getUsername());
			ps.setString(2, loguser.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("Exception was thrown: " + e.fillInStackTrace());
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("Exception was thrown: " + e.fillInStackTrace());
				e.printStackTrace();
			}
		return false;
	}

	@Override
	public boolean findUser(String username) throws UserInvalidException {
		// TODO Auto-generated method stub
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select * from project0." + this.user + " where " + this.name + " = ?";
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1,username); //1 refers to first ? to parameterize
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) { 
				throw new UserInvalidException();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		return false;
	}

}
