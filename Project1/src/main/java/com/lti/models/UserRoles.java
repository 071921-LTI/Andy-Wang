package com.lti.models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ERS_USER_ROLES")
public class UserRoles {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "ERS_USER_ROLE_ID", nullable = false)
	private int roleId;
	
	@Column(name = "USER_ROLE", nullable = false)
	private String role;
	
	public UserRoles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRoles(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + roleId;
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
		UserRoles other = (UserRoles) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserRoles [roleId=" + roleId + ", role=" + role + "]";
	}
	
}
