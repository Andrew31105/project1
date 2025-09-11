package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.check.CollectionJDBCUtil;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;


@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
	 	

	@Override
	public DistrictEntity findNameBy(Integer id) {
			 String sql = "SELECT d.name FROM district d WHERE d.id = " + id + ";";
			 DistrictEntity districtEntity = new DistrictEntity();
		       try (Connection conn = CollectionJDBCUtil.getConnection();
		             Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery(sql.toString())) {
	
		            while (rs.next()) {
		                districtEntity.setName(rs.getString("name"));
		            }
	
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return districtEntity;
	}
}
