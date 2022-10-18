package com.project.purchaseAnalysis.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.purchaseAnalysis.Dao.AdminRegestrationDao;
import com.project.purchaseAnalysis.models.AdminCredPojo;
import com.project.purchaseAnalysis.models.AdminLogInResponse;
import com.project.purchaseAnalysis.models.DoPredictResponse;
import com.project.purchaseAnalysis.models.PurchaseDataRequest;
import com.project.purchaseAnalysis.models.ResponseBodyPojo;
import com.project.purchaseAnalysis.models.UserDataRequestBody;
import com.project.purchaseAnalysis.models.UserDetailsRequest;
import com.project.purchaseAnalysis.models.UsersDetailsResponse;



@Service
public class AdminRegestrationService {

	
	@Autowired
	private AdminRegestrationDao adminRegestrationDao;
	
	@Value("${addUserDetails}")
	private String addUserDetails;
	
	
	@Value("${addPurchaseDetails}")
	private String addPurchaseDetails;
	
	
	@Value("${fetchPredictedData}")
	private String fetchPredictedData;
	
	
	@Value("${callPredictAAnalysis}")
	private String callPredictAAnalysis;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger logger = Logger.getLogger("AdminRegestrationService Class");
	
	
		public String regesterUserAdmin( AdminCredPojo adminCreds ) {
			
			return adminRegestrationDao.regesterAdminCreds(adminCreds);
			
		}

		public AdminLogInResponse logIn(AdminCredPojo adminCreds) {
			
			return adminRegestrationDao.authenticateAdmin(adminCreds);
			
		}
		
		
		
		public ResponseEntity<ResponseBodyPojo> uploadUserData( UserDataRequestBody userDataRequestBody ) throws URISyntaxException {
			



			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(addUserDetails);
			
			
			String uri = builder.build().encode().toString();
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			
			HttpEntity<UserDataRequestBody> httpEntity = new HttpEntity<UserDataRequestBody>(userDataRequestBody, headers);
			
			ResponseEntity<ResponseBodyPojo> responseEntity = null;
			
			responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResponseBodyPojo>() {});
			
			logger.log(Level.INFO, "Response From UserUpload service   "+responseEntity);
			
			return responseEntity;
			
		}

		
     public ResponseEntity<ResponseBodyPojo> uploadPurchaseData( PurchaseDataRequest purchaseData ) throws URISyntaxException {
			
			
             UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(addPurchaseDetails);
			String uri = builder.build().encode().toString();
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			
			HttpEntity<PurchaseDataRequest> httpEntity = new HttpEntity<PurchaseDataRequest>(purchaseData, headers);
			
			ResponseEntity<ResponseBodyPojo> responseEntity = null;
			
			responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResponseBodyPojo>() {});
			
			logger.log(Level.INFO, "Response From Purchase uploadUpload service   "+responseEntity);
			
			return responseEntity;
			
		}
     
     public ResponseEntity<UsersDetailsResponse> getPredictedPurchaseData() throws URISyntaxException {
			
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(fetchPredictedData);
			String uri = builder.build().encode().toString();
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			
			HttpEntity<PurchaseDataRequest> httpEntity = new HttpEntity<PurchaseDataRequest>(headers);
			
			ResponseEntity<UsersDetailsResponse> responseEntity = null;
			
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<UsersDetailsResponse>() {});
			
			logger.log(Level.INFO, "Response From predicted Purchase Data   "+responseEntity);
			
			return responseEntity;
			
		}
     
     public ResponseEntity<DoPredictResponse> doPredictPurchaseData() throws URISyntaxException {
			
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(callPredictAAnalysis);
			String uri = builder.build().encode().toString();
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			
			HttpEntity<PurchaseDataRequest> httpEntity = new HttpEntity<PurchaseDataRequest>(headers);
			
			ResponseEntity<DoPredictResponse> responseEntity = null;
			
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<DoPredictResponse>() {});
			
			logger.log(Level.INFO, "Response From Do Predict call   "+responseEntity);
			
			return responseEntity;
			
		}

		
}
	

