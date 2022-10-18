package com.project.purchaseAnalysis.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.purchaseAnalysis.models.PurchaseDataRequest;
import com.project.purchaseAnalysis.models.ResponseBodyPojo;
import com.project.purchaseAnalysis.models.UserDataRequestBody;
import com.project.purchaseAnalysis.services.PurchaseAnalysisDataUploadService;


@RestController
@RequestMapping("/api/v1")
public class PurchaseAnalysisDataUploadController {

	@Autowired
	private PurchaseAnalysisDataUploadService purchaseAnalysisDataUploadService;
	
	
	@PostMapping("/addUser")
	public ResponseEntity<ResponseBodyPojo> addUser(@RequestBody UserDataRequestBody userRequest) {
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap = purchaseAnalysisDataUploadService.addUserDetails(userRequest.getUser());
		
		if(!resultMap.isEmpty() && resultMap.containsKey("SUCCESS")) {
			
			ResponseBodyPojo res = new ResponseBodyPojo("Successfully Added The User", HttpStatus.ACCEPTED, resultMap);
			return new ResponseEntity<ResponseBodyPojo>(res, HttpStatus.OK);
		}
		else {
			ResponseBodyPojo res = new ResponseBodyPojo("Exceptions In The Request", HttpStatus.BAD_REQUEST, resultMap);
			return new ResponseEntity<ResponseBodyPojo>(res, HttpStatus.OK);
			
		}
		//return responseEntity;
	}
	
	@PostMapping("/addPurchaseData")
	public ResponseEntity<ResponseBodyPojo> addPurchaseData( @RequestBody PurchaseDataRequest purchaseData ) {
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap = purchaseAnalysisDataUploadService.addProductPurchaseData(purchaseData.getPurchaseDataList());
		if(!resultMap.isEmpty() && resultMap.containsKey("SUCCESS")) {
			ResponseBodyPojo res = new ResponseBodyPojo("Successfully Added The Purchase Data", HttpStatus.ACCEPTED, resultMap);
			return new ResponseEntity<ResponseBodyPojo>(res, HttpStatus.OK);
		}
		else {
			ResponseBodyPojo res = new ResponseBodyPojo("Exceptions In The Request", HttpStatus.BAD_REQUEST, resultMap);
			return new ResponseEntity<ResponseBodyPojo>(res, HttpStatus.OK);
		}
		//return responseEntity;
		
	}
	
	
	
}
