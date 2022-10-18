package com.project.purchaseAnalysis.controllers;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.purchaseAnalysis.models.AdminLogInResponse;
import com.project.purchaseAnalysis.models.AdminRegestrationRequestBody;
import com.project.purchaseAnalysis.models.DoPredictResponse;
import com.project.purchaseAnalysis.models.PurchaseDataRequest;
import com.project.purchaseAnalysis.models.ResponseBodyPojo;
import com.project.purchaseAnalysis.models.UserDataRequestBody;
import com.project.purchaseAnalysis.models.UsersDetailsResponse;
import com.project.purchaseAnalysis.services.AdminRegestrationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AdminRegrestrationController {

	
	@Autowired
	private AdminRegestrationService adminRegestrationService;
	
	
	@PostMapping("/regesterAdmin")
	public ResponseEntity<ResponseBodyPojo> regesterAdmin( @RequestBody AdminRegestrationRequestBody adminRegestrationRequestBody ) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if(adminRegestrationService.regesterUserAdmin(adminRegestrationRequestBody.getAdminCreds())
				.equalsIgnoreCase("Admin Successfully Added")) {
			ResponseBodyPojo res = new ResponseBodyPojo("Successfully Added The User Admin", HttpStatus.ACCEPTED, resultMap);
			return new ResponseEntity<ResponseBodyPojo>(res, HttpStatus.OK);
		}
		else {
			ResponseBodyPojo res = new ResponseBodyPojo("User Admin Regrestration Failure", HttpStatus.BAD_REQUEST, resultMap);
			return new ResponseEntity<ResponseBodyPojo>(res, HttpStatus.BAD_REQUEST);
		}
	}
		
	@PostMapping("/logIn")
	public ResponseEntity<AdminLogInResponse> userLogIn( @RequestBody AdminRegestrationRequestBody adminRegestrationRequestBody ) {
		
		AdminLogInResponse response = new AdminLogInResponse();
		response = adminRegestrationService.logIn(adminRegestrationRequestBody.getAdminCreds());
		
		return new ResponseEntity<AdminLogInResponse>(response, HttpStatus.OK);
		
		
	}
	
	@PostMapping("/addUserDetails")
	public ResponseEntity<ResponseBodyPojo> addUser( @RequestBody UserDataRequestBody request) throws URISyntaxException{
		return adminRegestrationService.uploadUserData(request);
	}
	
	@PostMapping("/addPurchaseData")
	public ResponseEntity<ResponseBodyPojo> addPurchaseData( @RequestBody PurchaseDataRequest purchaseData ) throws URISyntaxException {
		
		
		return adminRegestrationService.uploadPurchaseData(purchaseData);
		
		
		
	}
	
	@GetMapping("/getPredictedPurchaseData")
	public ResponseEntity<UsersDetailsResponse> getPredictedPurchaseData() throws URISyntaxException{
		return adminRegestrationService.getPredictedPurchaseData();
	}
	
	@GetMapping("/doPredict")
	public ResponseEntity<DoPredictResponse> predictPurchaseAnalysis() throws URISyntaxException {
		
		return adminRegestrationService.doPredictPurchaseData();
		
		
	}
}
