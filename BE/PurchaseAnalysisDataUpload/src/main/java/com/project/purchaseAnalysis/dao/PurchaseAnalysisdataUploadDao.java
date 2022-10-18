package com.project.purchaseAnalysis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.project.purchaseAnalysis.mappers.EmailIDMapper;
import com.project.purchaseAnalysis.models.EmailIdValues;
import com.project.purchaseAnalysis.models.PurchaseData;
import com.project.purchaseAnalysis.models.UserDetailsRequest;


@Component
public class PurchaseAnalysisdataUploadDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String SELECT_ALL_EMAILS = "select user_emailId from `user_management_db`.`user_details`";
	
	private static final String INSERT_USER_DETAILS = "INSERT INTO `user_management_db`.`user_details`\r\n" 
			+ "(`user_name`, `user_state`, `user_emailId`, `user_age`, `user_country`, `user_loyaltyLevel`, `user_predictedPurchase`)\r\n"
			+ " VALUES (:user_name, :user_state, :user_emailId, :user_age, :user_country, :user_loyaltyLevel, :user_predictedPurchase)";
	
	private static final String INSERT_PURCHASE_DETAILS = "INSERT INTO `user_management_db`.`users_purchase_data`\r\n"
			+ "(`user_emailId`, `purchase_date`, `product_Id`)\r\n"
			+ " VALUES (:user_emailId, :purchase_date, :product_Id)";
	
	
			
	
	@Transactional
	public int insertUserDetails(UserDetailsRequest request) {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		paramsMap.put("user_name", request.getUserName());
		paramsMap.put("user_state", request.getUserState());
		paramsMap.put("user_emailId", request.getEmailId());
		paramsMap.put("user_age", request.getUserAge());
		paramsMap.put("user_country", request.getUserCountry());
		paramsMap.put("user_loyaltyLevel", request.getUserLoyaltyLevel() != null ?request.getUserLoyaltyLevel():"");
		paramsMap.put("user_predictedPurchase", request.getUserPredictedPurchase() != null ? request.getUserPredictedPurchase() : "");
		 
		return namedParameterJdbcTemplate.update(INSERT_USER_DETAILS, paramsMap);
		
	}
	
	
	public List<EmailIdValues> fetchEmails() {
		
		return namedParameterJdbcTemplate.query(this.SELECT_ALL_EMAILS, new EmailIDMapper());
		
	}
	
	
	@Transactional
	public int[] insertPurchaseData(List<PurchaseData> purchaseData) {
		
		Map<String, ?>[] batchValues = new HashMap[purchaseData.size()];
		List<Map<String, ?>> paramValues = new ArrayList<Map<String, ?>>();
		for( PurchaseData data : purchaseData) {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("user_emailId", data.getUserEmailId());
			paramsMap.put("purchase_date", data.getPurchaseDate());
			paramsMap.put("product_Id", data.getProductId());
			paramValues.add(paramsMap);
		}
		
		return namedParameterJdbcTemplate.batchUpdate(INSERT_PURCHASE_DETAILS, paramValues.toArray(batchValues));
		
	}
	
	
	
}
