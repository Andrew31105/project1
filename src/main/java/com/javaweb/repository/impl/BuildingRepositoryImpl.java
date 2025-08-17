package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;


@Repository
public class BuildingRepositoryImpl implements BuildingRepository  {

	static final String DB_URL = "jdbc:mysql://localhost:3306/building_database1";
    static final String USER = "root";       
    static final String PASSWORD = "Daoanh123!"; 
    
    @Override
    public List<BuildingEntity> getAllBuildings(String name,Long district) {
        List<BuildingEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM buildings b Where 1 = 1");
        if(name != null && !name.equals("")) {
            sql.append(" AND b.building_name = '" + name + "'");
        }

        if(district != null){
        	sql.append(" AND b.district = " + district + " ");
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                BuildingEntity building = new BuildingEntity();
                building.setName(rs.getString("building_name"));
                building.setWard(rs.getString("ward"));
                building.setStreet(rs.getString("street"));
                result.add(building);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
