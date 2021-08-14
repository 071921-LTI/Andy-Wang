package com.lti.models;

import javax.persistence.*;

@Entity
@Table(name = "ERS_USERS")
public class User {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ERS_USERS_ID")
	private int id;
	
	@Column(name = "USER_FIRST_NAME",nullable = false)
	private String firstname;
	
	@Column(name = "USER_LAST_NAME", nullable = false)
	private String lastname;
	
	@Column(name = "ERS_USERNAME", nullable = false ,unique = true)
	private String username;

	@Column(name = "ERS_PASSWORD",nullable = false,unique = true)
	private String password;
	
	@Column(name = "USER_EMAIL",nullable = false)
	private String email;
	
	@ManyToOne(targetEntity = UserRoles.class)
	@JoinColumn(name = "user_role_id", nullable = false)
	private UserRoles roleid;
	
	
	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String firstname, String lastname, String username, String password, String email, UserRoles roleid) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.roleid = roleid;
		this.email = email;
	}
	
	public User(String firstname, String lastname, UserRoles roleid) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.roleid = roleid;
	}

	public User(int id, UserRoles roleid) {
		super();
		this.id = id;
		this.roleid = roleid;
	}

	public User(String firstname, String lastname, String username, String password, String email, UserRoles roleid) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.roleid = roleid;
		this.email = email;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRoles getRoleid() {
		return roleid;
	}
	public void setRole(UserRoles roleid) {
		this.roleid = roleid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roleid == null) ? 0 : roleid.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roleid == null) {
			if (other.roleid != null)
				return false;
		} else if (!roleid.equals(other.roleid))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", roleid=" + roleid + "]";
	}
	

}