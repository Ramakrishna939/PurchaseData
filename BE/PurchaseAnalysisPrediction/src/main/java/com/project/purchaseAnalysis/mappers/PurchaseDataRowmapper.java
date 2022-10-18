package com.project.purchaseAnalysis.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.purchaseAnalysis.models.PurchaseData;


public class PurchaseDataRowmapper implements RowMapper<PurchaseData> {

	@Override
	public PurchaseData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		PurchaseData data = new PurchaseData();
		data.setProductId(rs.getString("product_Id"));
		data.setPurchaseDate(rs.getString("purchase_date"));
		data.setUserEmailId(rs.getString("user_emailId"));
		return data;
	}
}
