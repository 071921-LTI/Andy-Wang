package com.lti.daos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.lti.exceptions.UserInvalidException;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public class UserFile implements UserDao {
	File userFile;
	//= new File("src/main/resources/users.txt");
	
	public UserFile (String file) {
		userFile = new File("src/main/resources/"+ file + ".txt");
	}
	

	@Override
	public User getUser(String username) throws UserNotFoundException {
		
		// try-with-resources: allows to close resources automatically without calling the .close() method
		try(BufferedReader reader = new BufferedReader(new FileReader(userFile))){
			// grabs a line from user.txt
			String currentLine = reader.readLine();
			
			// if currentLine == null, it means we reached the end of the file
			while(currentLine != null) {
				// b/c the user info is stored as uname:pass, needs to be split
				String[] userFields = currentLine.split(":");
				// compares first element of the array which is the stored name
				if(userFields[0].equals(username)) {
					return new User(userFields[0], userFields[1]);
				}
				currentLine = reader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new UserNotFoundException();
	}

	@Override
	public boolean addUser(User user) {
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true))){
			writer.write("\n" + user.getUsername()+":"+user.getPassword());
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}


	@Override
	public boolean findUser(String username) throws UserInvalidException {
		// try-with-resources: allows to close resources automatically without calling the .close() method
				try(BufferedReader reader = new BufferedReader(new FileReader(userFile))){
					// grabs a line from user.txt
					String currentLine = reader.readLine();
					
					// if currentLine == null, it means we reached the end of the file
					while(currentLine != null) {
						// b/c the user info is stored as uname:pass, needs to be split
						String[] userFields = currentLine.split(":");
						// compares first element of the array which is the stored name
						if(userFields[0].equals(username)) {
							throw new UserInvalidException();
						}
						currentLine = reader.readLine();
					}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;		
	}


}
