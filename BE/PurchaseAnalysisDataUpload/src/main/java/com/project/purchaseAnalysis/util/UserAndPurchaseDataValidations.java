package com.project.purchaseAnalysis.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.purchaseAnalysis.dao.PurchaseAnalysisdataUploadDao;
import com.project.purchaseAnalysis.models.EmailIdValues;
import com.project.purchaseAnalysis.models.PurchaseData;
import com.project.purchaseAnalysis.models.UserDetailsRequest;

@Component
public class UserAndPurchaseDataValidations {

	@Autowired
	private PurchaseAnalysisdataUploadDao purchaseAnalysisdataUploadDao;
	
	private static final String coDomain = "email.com";
	private static final String EMAIL_PATTERN = 
	    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	    + Pattern.quote(coDomain) + "$";
	
    

	
	public HashMap<String, String> validateUserDetails(UserDetailsRequest user) {
		
		HashMap<String, String> exceptionsMap = new HashMap<String, String>();
		
		//email validation
		Pattern pat = Pattern.compile(EMAIL_PATTERN);
		if(user.getEmailId().isEmpty()) {
			exceptionsMap.put("No_EMAIL", "Plese provide email id");
		}
		else if(!pat.matcher(user.getEmailId()).matches()) {
			exceptionsMap.put("INVALID_EMAIL", "Please provide valid Email Id");
		}
		else {
			
			List<EmailIdValues> emailList = new ArrayList<EmailIdValues>();
			
			emailList = this.purchaseAnalysisdataUploadDao.fetchEmails();
			for(EmailIdValues email : emailList) {
				
				if(email.getEmailId().equalsIgnoreCase(user.getEmailId())) {
					exceptionsMap.put("EMAIL_ALREADY_EXISTS", "Provided Email Already Exists, Please Provide Unique Email Id");
				}
			}
			
		}
		
		
		//age validation
		if(!(user.getUserAge() > 18 && user.getUserAge() < 100)) {
			exceptionsMap.put("INVALID_AGE", "User age should be greater than 18 and less than 100");
		}
		
		//country validation
		Map<String, String> countries = new HashMap<>();
		  for (String iso : Locale.getISOCountries()) {
		    Locale l = new Locale("", iso);
		    countries.put(l.getDisplayCountry(), iso);
		  }
		if(!countries.containsKey(user.getUserCountry())) {
			exceptionsMap.put("INVALID_COUNTRY", "Please provide a valid country name for user");
			
		}
		  
		return exceptionsMap;
		
	}
	
	public HashMap<String, String> validatePurchaseData(List<PurchaseData> purchaseData) {
		
		HashMap<String, String> exceptionsMap = new HashMap<String, String>();
		
		for(PurchaseData data: purchaseData) {
			if(data.getUserEmailId().isEmpty()) {
				exceptionsMap.put("NO_EMAIL", "Email Id should be provided");
			}
			if(data.getProductId().isEmpty()) {
				exceptionsMap.put("NO_PRODUCTID", "Product Id should not be null");
			}
			if(data.getPurchaseDate().isEmpty()) {
				exceptionsMap.put("NO_PURCHASEDATE", "Purchase Date Should not be null");
			}
		}
		
		return exceptionsMap;
	}
	
}
