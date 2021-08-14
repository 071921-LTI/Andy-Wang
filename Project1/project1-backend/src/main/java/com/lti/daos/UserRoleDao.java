package com.lti.daos;
import com.lti.models.UserRoles;

public interface UserRoleDao {
	UserRoles addUserRole(UserRoles role);
	UserRoles getUserRoleById(int id);
	void deleteUserRole(UserRoles role);
}
