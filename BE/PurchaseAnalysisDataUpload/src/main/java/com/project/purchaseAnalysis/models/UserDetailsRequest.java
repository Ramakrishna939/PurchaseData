package com.project.purchaseAnalysis.models;

public class UserDetailsRequest {

	private long id;
	private String userName;
	private String userState;
	private String emailId;
	private long userAge;
	private String userCountry;
	private String userLoyaltyLevel;
	private String userPredictedPurchase;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getUserAge() {
		return userAge;
	}
	public void setUserAge(long userAge) {
		this.userAge = userAge;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	public String getUserLoyaltyLevel() {
		return userLoyaltyLevel;
	}
	public void setUserLoyaltyLevel(String userLoyaltyLevel) {
		this.userLoyaltyLevel = userLoyaltyLevel;
	}
	public String getUserPredictedPurchase() {
		return userPredictedPurchase;
	}
	public void setUserPredictedPurchase(String userPredictedPurchase) {
		this.userPredictedPurchase = userPredictedPurchase;
	}
	
	
	
	
	
}
