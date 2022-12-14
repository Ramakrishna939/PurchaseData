package com.project.purchaseAnalysis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.purchaseAnalysis.models.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long>  {

	Users findUsersByUsername(String username);
}
