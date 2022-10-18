package com.project.purchaseAnalysis.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.project.purchaseAnalysis.models.EmailIdValues;

public class EmailIDMapper implements RowMapper<EmailIdValues>{

	@Override
	public EmailIdValues mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		EmailIdValues emailIdValues = new EmailIdValues();
		emailIdValues.setEmailId(rs.getString("user_emailId"));
		
		return emailIdValues;
	}
	
}
