package com.project.purchaseAnalysis.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.purchaseAnalysis.dao.PurchaseAnalysisdataUploadDao;
import com.project.purchaseAnalysis.models.PurchaseData;
import com.project.purchaseAnalysis.models.UserDetailsRequest;
import com.project.purchaseAnalysis.util.UserAndPurchaseDataValidations;


@Service
public class PurchaseAnalysisDataUploadService {

	
private static final Logger logger = Logger.getLogger("PurchaseAnalysisDataUploadService Class");
	
	@Autowired
	private UserAndPurchaseDataValidations validateUserDetails;
	
	@Autowired
	private PurchaseAnalysisdataUploadDao purchaseAnalysisdataUploadDao;
	
	public Map<String, String> addUserDetails(UserDetailsRequest user) {
		
		int result = 0;
		Map<String, String> exceptionMap = new HashMap<String, String>();
		Map<String, String> resultMap = new HashMap<String, String>();
		
		exceptionMap = validateUserDetails.validateUserDetails(user);
		
		if(exceptionMap.isEmpty()) {
			
			 result = purchaseAnalysisdataUploadDao.insertUserDetails(user);
				if (result > 0) {
					resultMap.put("SUCCESS", "Sucsess");
				} else {
					resultMap = exceptionMap;
				}
		}
		else {
			resultMap = exceptionMap;
		}
		
		return resultMap;
		
		
		
		
	}
	
	
	public Map<String, String> addProductPurchaseData(List<PurchaseData> purchaseDataList) {
		Map<String, String> exceptionMap = new HashMap<String, String>();
		int[]  resultCount;
		Map<String, String> resultMap = new HashMap<String, String>();
		exceptionMap = validateUserDetails.validatePurchaseData(purchaseDataList);
		if(exceptionMap.isEmpty()) {
			resultCount = this.purchaseAnalysisdataUploadDao.insertPurchaseData(purchaseDataList);
			if(resultCount.length > 0) {
				resultMap.put("SUCCESS", "Sucsess");
			} else {
				resultMap = exceptionMap;
			}
		}
		else {
			resultMap = exceptionMap;
		}
		return resultMap;
	}
	
	
	
	
}
