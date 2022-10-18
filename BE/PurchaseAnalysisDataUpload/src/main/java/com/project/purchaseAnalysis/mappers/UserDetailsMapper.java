package com.project.purchaseAnalysis.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.project.purchaseAnalysis.models.UserDetailsRequest;

public class UserDetailsMapper implements RowMapper<UserDetailsRequest> {

	@Override
	public UserDetailsRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserDetailsRequest request = new UserDetailsRequest();
		
		request.setEmailId(rs.getString("user_emailId"));
		request.setUserName(rs.getString("user_name"));
		request.setId(rs.getLong("user_id"));
		request.setUserAge(rs.getLong("user_age"));
		request.setUserCountry(rs.getString("user_country"));
		request.setUserState(rs.getString("user_state"));
		request.setUserPredictedPurchase(rs.getString("user_predictedPurchase"));
		request.setUserLoyaltyLevel(rs.getString("user_loyaltyLevel"));
		return request;
	}
	
}
