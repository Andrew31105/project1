package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.check.Check_Null;
import com.javaweb.check.Check_Numbers;
import com.javaweb.check.CollectionJDBCUtil;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;






@Repository
public class BuildingRepositoryImpl implements BuildingRepository  {

    public static String Join_Table(Map<String,Object> params,List<String> typeCode, StringBuilder sql) {
    	String staff_Id = (String)params.get("staffId");
    	if(staff_Id != null) {
    		sql.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.staffid");
    	}
    	if(typeCode != null && typeCode.size() != 0) {
    		sql.append(" INNER JOIN buildingrenttype bt ON b.id = bt.buildingid");
    		sql.append(" INNER JOIN renttype rt ON bt.renttypeid = rt.id");
    	}
    	
    	String rentAreaFrom = (String)params.get("rentAreaFrom");
    	String rentAreaTo = (String)params.get("rentAreaTo");
    	if(rentAreaFrom != null && !rentAreaFrom.equals("") || rentAreaTo != null && !rentAreaTo.equals("") ) {
    		sql.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid");
    	}
    	return sql.toString();
    }
    
    public static String Query(Map<String,Object> params,List<String> typeCode, StringBuilder where) {
    	for(Map.Entry<String,Object> it : params.entrySet()) {
    		if(!it.getKey().equals("staffId") && !it.getKey().equals("rentAreaFrom") && 
    		!it.getKey().equals("rentAreaTo") && !it.getKey().equals("rentPriceFrom") && !it.getKey().equals("typeCode")
    		&& !it.getKey().equals("rentPriceTo")) {
    			String value = it.getValue().toString();
    			if(Check_Numbers.checkNumbers(value) == true) {
    				where.append(" AND b." + it.getKey() + "= " + value);
    			}
    			else {
    				where.append(" AND b." + it.getKey() + " LIKE '%" + value + "%' ");
    			}
    		}
    	}
    	
    	
    	return where.toString();
    }
    
    public static String Special_Query(Map<String,Object> params,List<String> typeCode, StringBuilder where) {
    	String staff_Id = (String)params.get("staffId");
    	if(Check_Null.check_Null(staff_Id)) {
    		where.append(" AND staff.id = "  + staff_Id);
    	}
    	String rentPriceFrom = (String)params.get("rentPriceFrom");
    	String rentPriceTo = (String)params.get("rentPriceTo");
    	
    	if(Check_Null.check_Null(rentPriceFrom) || Check_Null.check_Null(rentPriceTo)) {
    		if(Check_Numbers.checkNumbers(rentPriceFrom)) {
    			where.append(" AND b.rentprice >= " + rentPriceFrom);
    		}
    		if(Check_Numbers.checkNumbers(rentPriceTo)) {
    			where.append(" AND b.rentPrice <= " + rentPriceTo);
    		}
    	}
    	
    	String rentAreaFrom =(String)params.get("rentAreaFrom");
    	String rentAreaTo =(String)params.get("rentAreaTo");
    	if(Check_Null.check_Null(rentAreaFrom) || Check_Null.check_Null(rentAreaTo)) {
    		if(Check_Numbers.checkNumbers(rentAreaFrom)) {
    			where.append(" AND ra.value >= " + rentAreaFrom);
    		}
    		if(Check_Numbers.checkNumbers(rentAreaTo)) {
    			where.append(" AND ra.value <= " + rentAreaTo);
    		}
    	}
    	
    	if(typeCode != null && typeCode.size() != 0) {
    		List<String> code = new ArrayList<>();
    		for(String it : typeCode) {
    			code.add("'" + it + "'");
    		}
    		where.append(" AND rt.code IN (" + String.join(",", code) + ") ");

    		
    	}
    	
    	return where.toString();
    }
    
    @Override
    public List<BuildingEntity> findAll(Map<String,Object> params,List<String> typeCode) {
        List<BuildingEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT b.id, b.districtId, b.name ,"
        		+ " b.ward, b.street, b.districtid , b.servicefee, "
        		+ "b.numberofbasement, b.floorarea, b.managername,b.brokeragefee,"
        		+ "b.managerphonenumber,b.rentprice, b.direction from building b");
        Join_Table(params,typeCode,sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        Query(params,typeCode,where);
        Special_Query(params,typeCode,where);
        where.append("GROUP BY b.id;");
        sql.append(where);
        try (Connection conn =  CollectionJDBCUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                BuildingEntity building = new BuildingEntity();
                building.setId(rs.getInt("b.id"));
                building.setName(rs.getString("b.name"));
                building.setWard(rs.getString("b.ward"));
                building.setStreet(rs.getString("street"));
                building.setBrokeRangeFee(rs.getInt("b.districtId"));
                building.setDistrictId(rs.getInt("b.districtId"));
                building.setFloorArea(rs.getInt("b.floorarea"));
                building.setManagerName(rs.getString("b.managername"));
                building.setManagerPhoneNumbers(rs.getString("b.managerphonenumber"));
                building.setNumberOfBasement(rs.getInt("b.numberofbasement"));
                building.setRentPrice(rs.getInt("b.rentprice"));

                result.add(building);
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}


