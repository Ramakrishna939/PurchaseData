package com.project.purchaseAnalysis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.purchaseAnalysis.models.UsersRoles;

public interface UsersRolesRepo extends JpaRepository<UsersRoles, Long>{

	UsersRoles findUsersRolesByUserid(long userid);
	
}
