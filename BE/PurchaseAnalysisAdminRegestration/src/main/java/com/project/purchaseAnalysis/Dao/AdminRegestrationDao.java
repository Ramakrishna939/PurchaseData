package com.project.purchaseAnalysis.Dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.project.purchaseAnalysis.models.AdminCredPojo;
import com.project.purchaseAnalysis.models.AdminLogInResponse;
import com.project.purchaseAnalysis.models.Users;
import com.project.purchaseAnalysis.models.UsersRoles;
import com.project.purchaseAnalysis.repositories.UserRepository;
import com.project.purchaseAnalysis.repositories.UsersRolesRepo;

@Component
public class AdminRegestrationDao {

	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UsersRolesRepo usersRolesRepo;
	
	private static final String INSERT_ADMIN_CREDS = "INSERT INTO `user_management_db`.`users`\r\n" 
			+ "(`user_name`, `user_password`)\r\n"
			+ " VALUES (:user_name, :user_password)";
	
	private static final String INSERT_ADMIN_ROLES = "INSERT INTO `user_management_db`.`users_roles`\r\n" 
			+ "(`userid`, `roleid`)\r\n"
			+ " VALUES (:userid, :roleid)";
	
	
	public AdminLogInResponse authenticateAdmin(AdminCredPojo adminCreds) {
		
		Users resultedUser = userRepository.findUsersByUsername(adminCreds.getUsername());
		String authenticationStatus = "";
		String roleName = "";
		AdminLogInResponse adminLogInResponse = new AdminLogInResponse();
		if(resultedUser.getPassword().equalsIgnoreCase(adminCreds.getPassword())) {
			authenticationStatus = "Successfully Authenticated";
			UsersRoles usersRoles = new UsersRoles();
			usersRoles = usersRolesRepo.findUsersRolesByUserid(resultedUser.getId());
			if(usersRoles.getRoleid() == 1) {
				roleName = "ADMIN";
			}
			else {
				roleName = "SUPERADMIN";
			}
			
		}
		else {
			authenticationStatus = "Authentication Failed";
		}
		adminLogInResponse.setMessage(authenticationStatus);
		adminLogInResponse.setRoleName(roleName);
		return adminLogInResponse;
	}
	
	public String regesterAdminCreds( AdminCredPojo adminCreds ) {
		
        
		Users userAdminRequest = new Users();
		int result = 0;
		userAdminRequest.setUsername(adminCreds.getUsername());
		userAdminRequest.setPassword(adminCreds.getPassword());
		
		Users resultedUser = userRepository.save(userAdminRequest);
		
		if(resultedUser != null) {
			Map<String, Long> paramsMap = new HashMap<String, Long>();
			
			paramsMap.put("userid", resultedUser.getId());
			paramsMap.put("roleid", (long) 1);
			result = namedParameterJdbcTemplate.update(INSERT_ADMIN_ROLES, paramsMap);
			
		}
		
		if(result > 0) {
			return "Admin Successfully Added";
		}
		else {
			return "Admin Regestration Failed";
		}
		
	}
	
	
}
