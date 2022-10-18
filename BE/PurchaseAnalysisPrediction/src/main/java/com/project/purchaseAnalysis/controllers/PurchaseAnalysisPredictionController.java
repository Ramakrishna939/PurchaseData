package com.project.purchaseAnalysis.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.purchaseAnalysis.models.DoPredictResponse;
import com.project.purchaseAnalysis.models.UserDetailsRequest;
import com.project.purchaseAnalysis.models.UsersDetailsResponse;
import com.project.purchaseAnalysis.services.PurchaseAnalysisPredictionService;

@RestController
@RequestMapping("api/v1")
public class PurchaseAnalysisPredictionController {

	@Autowired
	private PurchaseAnalysisPredictionService purchaseAnalysisPredictionService;
	
	@GetMapping("/getPredictedPurchaseData")
	public UsersDetailsResponse getPredictedUserPurchaseData() {
		
		List<UserDetailsRequest> usersList = new ArrayList<UserDetailsRequest>();
		UsersDetailsResponse response = new UsersDetailsResponse();
		usersList = purchaseAnalysisPredictionService.fetchUsers();
		if(usersList.size()>0) {
			response.setMessage("Success");
			response.setUsersList(usersList);
		}
		else {
			response.setMessage("There is No Data Or There has been some Issue");
		}
		return response;
		
	}
	
	@GetMapping("/doPredict")
	public ResponseEntity<DoPredictResponse> predictPurchaseAnalysis() {
		
		String result = "";
		result = purchaseAnalysisPredictionService.predictPurchaseAnalysis();
		DoPredictResponse response = new DoPredictResponse();
		response.setMessage(result);
		return new ResponseEntity<DoPredictResponse>(response, HttpStatus.OK);
	}
	
}
