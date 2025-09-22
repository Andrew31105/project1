package com.javaweb.repository.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.check.CollectionJDBCUtil;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
	
    
	@Override
	public List<RentAreaEntity> getValueByBuildingId(Integer id) {
		String sql = "SELECT * FROM rentarea WHERE buildingid = " + id;
		List<RentAreaEntity> rentareas = new ArrayList<>();
		
		try (Connection conn = CollectionJDBCUtil.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql.toString())) {

	            while (rs.next()) {
	            	RentAreaEntity rentAreaEntity = new RentAreaEntity();
	            	rentAreaEntity.setValue(rs.getInt("value"));
	            	rentareas.add(rentAreaEntity);
	            }
	            

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return rentareas;
	} 
	
}
