package com.project.purchaseAnalysis.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.project.purchaseAnalysis.mappers.PurchaseDataRowmapper;
import com.project.purchaseAnalysis.mappers.UserDetailsMapper;
import com.project.purchaseAnalysis.models.PurchaseData;
import com.project.purchaseAnalysis.models.UserDetailsRequest;


@Component
public class PurchaseAnalysisPredictionDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String SELECT_USER_DETAILS = "SELECT * FROM `user_management_db`.`user_details`";
	
	private static final String SELECT_USER_DETAILS_FROM_PREDICTION_DB = "SELECT * FROM `user_prediction_db`.`user_details`";
	
	private static final String SELECT_USER_PURCHASE_DATA = "SELECT * FROM `user_management_db`.`users_purchase_data`\r\n"
			+ " WHERE `user_emailId`= :user_emailId";
	
	private static final String INSERT_USER_DETAILS_PREDICTION_DB = "INSERT INTO `user_prediction_db`.`user_details`\r\n" 
			+ "(`user_name`, `user_state`, `user_emailId`, `user_age`, `user_country`, `user_loyaltyLevel`, `user_predictedPurchase`)\r\n"
			+ " VALUES (:user_name, :user_state, :user_emailId, :user_age, :user_country, :user_loyaltyLevel, :user_predictedPurchase)";
	
	private static final String DELETE_USER_DATA_IN_PREDICTION_DB = "Delete FROM `user_prediction_db`.`user_details`";
	
	
	
public List<UserDetailsRequest> fetchUserData() {
		
		return namedParameterJdbcTemplate.query(SELECT_USER_DETAILS, new UserDetailsMapper());
		
	}
	
	public List<UserDetailsRequest> fetchUserDataFromPredictionDb() {

		return namedParameterJdbcTemplate.query(SELECT_USER_DETAILS_FROM_PREDICTION_DB, new UserDetailsMapper());

	}
	
	
	public List<PurchaseData>  fetchPurchaseData(String emailId) {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("user_emailId", emailId);
		return this.namedParameterJdbcTemplate.query(SELECT_USER_PURCHASE_DATA,paramsMap, new PurchaseDataRowmapper());
		
	}
	
	@Transactional
	public int saveUserInPredictionDbTable(UserDetailsRequest request) {
		
        Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		paramsMap.put("user_name", request.getUserName());
		paramsMap.put("user_state", request.getUserState());
		paramsMap.put("user_emailId", request.getEmailId());
		paramsMap.put("user_age", request.getUserAge());
		paramsMap.put("user_country", request.getUserCountry());
		paramsMap.put("user_loyaltyLevel", request.getUserLoyaltyLevel());
		paramsMap.put("user_predictedPurchase", request.getUserPredictedPurchase());
		 
		return namedParameterJdbcTemplate.update(INSERT_USER_DETAILS_PREDICTION_DB, paramsMap);
		
	}
	
	
}
