package com.project.purchaseAnalysis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.purchaseAnalysis.Dao.PurchaseAnalysisPredictionDao;
import com.project.purchaseAnalysis.models.PurchaseData;
import com.project.purchaseAnalysis.models.UserDetailsRequest;

@Service
public class PurchaseAnalysisPredictionService {

	@Autowired
	private PurchaseAnalysisPredictionDao purchaseAnalysisPredictionDao;
	
	private static final Logger logger = Logger.getLogger("PurchaseAnalysisPredictionService Class");
	
public String predictPurchaseAnalysis() {
		
		//get all users
		List<UserDetailsRequest> users = new ArrayList<UserDetailsRequest>();
		List<PurchaseData> purchaseDetails = new ArrayList<PurchaseData>();
		users = this.purchaseAnalysisPredictionDao.fetchUserData();
		String userLoyality = "";
		String predictedProdId = "";
		String returnResult = "";
		int result = 0;
		if(users.size()>0) {
			
			for(UserDetailsRequest user : users) {
				if(!user.getEmailId().isBlank()) {
					purchaseDetails = this.purchaseAnalysisPredictionDao.fetchPurchaseData(user.getEmailId());
					if(purchaseDetails.size() > 1) {
						userLoyality = "High";
					}
					else if(purchaseDetails.size() == 1) {
						userLoyality = "Meadium";
					}
					else if(purchaseDetails.size() == 0 || purchaseDetails.isEmpty()) {
						userLoyality = "Low";
					}
					
					if(purchaseDetails.size() > 0 && !userLoyality.isEmpty()) {
						PurchaseData[] purchaseArray = new PurchaseData[purchaseDetails.size()];
						purchaseDetails.toArray(purchaseArray);
						predictedProdId = mostFrequentlyPurchasedProduct(purchaseArray, purchaseArray.length);
						logger.log(Level.INFO, "Most frequently bought product"+user.getEmailId()+" " +predictedProdId);
						user.setUserLoyaltyLevel(userLoyality);
						user.setUserPredictedPurchase(predictedProdId);
						// adding to new predict db users table
						result = this.purchaseAnalysisPredictionDao.saveUserInPredictionDbTable(user);
						if(result>0) {
							logger.log(Level.INFO, "Successfully added the record in predit db user table "+user.getEmailId()+" " +user.getUserPredictedPurchase());
							returnResult = "SUCCESS";
						}
						else if(result <= 0) {
							logger.log(Level.INFO, "Failed to add the record in predit db user table "+user.getEmailId()+" " +user.getUserPredictedPurchase());
							returnResult = "FAILURE";
						}
					}
					
				}
				
			}
			
		}
		
		return returnResult;
	}
	
	public String mostFrequentlyPurchasedProduct( PurchaseData[] arr, int n) {
		
		
		int maxcount = 0;
	    String element_having_max_freq = null;
	    for (int i = 0; i < n; i++) {
	      int count = 0;
	      for (int j = 0; j < n; j++) {
	        if (arr[i].getProductId().equalsIgnoreCase(arr[j].getProductId())) {
	          count++;
	        }
	      }
	 
	      if (count > maxcount) {
	        maxcount = count;
	        element_having_max_freq = arr[i].getProductId();
	      }
	    }
	 
	    return element_having_max_freq;

		
		
	}
		
	public List<UserDetailsRequest> fetchUsers() {

		return this.purchaseAnalysisPredictionDao.fetchUserDataFromPredictionDb();

	}
	
	
	
	
}
