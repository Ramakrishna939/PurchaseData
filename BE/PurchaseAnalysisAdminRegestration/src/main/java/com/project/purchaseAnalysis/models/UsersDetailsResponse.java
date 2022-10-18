package com.project.purchaseAnalysis.models;

import java.util.List;

public class UsersDetailsResponse {

	private List<UserDetailsRequest> usersList;

	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<UserDetailsRequest> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UserDetailsRequest> usersList) {
		this.usersList = usersList;
	}
	
	
}
