package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.check.CollectionJDBCUtil;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import javax.persistence.Query;



@Repository
@Primary
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom  {

	@PersistenceContext
	private EntityManager entityManager;
	
    public static String Join_Table(BuildingSearchBuilder buildingSearchBuilder , StringBuilder sql) {
    	Integer staff_Id = buildingSearchBuilder.getStaffId();
    	if(staff_Id != null) {
    		sql.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.staffid");
    	}
    	List<String> typeCode = buildingSearchBuilder.getTypeCode();
    	if(typeCode != null && typeCode.size() != 0) {
    		sql.append(" INNER JOIN buildingrenttype bt ON b.id = bt.buildingid");
    		sql.append(" INNER JOIN renttype rt ON bt.renttypeid = rt.id");
    	}
    	
    	Integer rentAreaFrom = buildingSearchBuilder.getAreaFrom();
    	Integer rentAreaTo = buildingSearchBuilder.getAreaTo();
    	if(rentAreaFrom != null  || rentAreaTo != null) {
    		sql.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid");
    	}
    	return sql.toString();
    }
    
    public static String Query(BuildingSearchBuilder buildingSearchBuilder , StringBuilder where) {
    	try {
    		Field[] fields =  buildingSearchBuilder.getClass().getDeclaredFields();
    		for(Field item : fields) {
    			item.setAccessible(true);
    			String fieldName = item.getName();
    			if(!fieldName.equals("staffId") && !fieldName.equals("areaFrom") && 
    					!fieldName.equals("areaTo") && !fieldName.equals("rentPriceFrom") && !fieldName.equals("typeCode")
    					&& !fieldName.equals("rentPriceTo")) {
    				
    				Object value = item.get(buildingSearchBuilder);
    				if(value != null) {
    				if(item.getType().equals("java.lang.Integer")) {
        				where.append(" AND b." + fieldName + "= " + value);
        			}
        			else {
        				where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
        			}
    				}
    			}
    			
    		}
    	}
    	
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	return where.toString();
    }
    
    public static String Special_Query(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
    	Integer staff_Id = buildingSearchBuilder.getStaffId();
    	if(staff_Id != null) {
    		where.append(" AND staff.id = "  + staff_Id);
    	}
    	Integer rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
    	Integer rentPriceTo = buildingSearchBuilder.getRentPriceTo();
    	
    	
    		if(rentPriceFrom != null) {
    			where.append(" AND b.rentprice >= " + rentPriceFrom);
    		}
    		if(rentPriceTo != null) {
    			where.append(" AND b.rentPrice <= " + rentPriceTo);
    		}
    	
    	
    	Integer rentAreaFrom = buildingSearchBuilder.getAreaFrom();
    	Integer rentAreaTo =buildingSearchBuilder.getAreaTo();
  
    		if(rentAreaFrom != null) {
    			where.append(" AND ra.value >= " + rentAreaFrom);
    		}
    		if(rentAreaTo != null) {
    			where.append(" AND ra.value <= " + rentAreaTo);
    		}
    	
    	List<String> typeCode = buildingSearchBuilder.getTypeCode();
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
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        List<BuildingEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT b.id, b.districtId, b.name ,"
        		+ " b.ward, b.street, b.districtid , b.servicefee, "
        		+ "b.numberofbasement,b.level , b.floorarea, b.managername,b.brokeragefee,"
        		+ "b.managerphonenumber,b.rentprice, b.direction from building b");
        Join_Table(buildingSearchBuilder,sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        Query(buildingSearchBuilder,where);
        Special_Query(buildingSearchBuilder,where);
        where.append("GROUP BY b.id;");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }}

//	@Override
//	public void addBuilding(BuildingEntity buildingEntity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<BuildingEntity> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<BuildingEntity> findAll(Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<BuildingEntity> findAllById(Iterable<Long> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends BuildingEntity> List<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void flush() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public <S extends BuildingEntity> S saveAndFlush(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteInBatch(Iterable<BuildingEntity> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAllInBatch() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public BuildingEntity getOne(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends BuildingEntity> List<S> findAll(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends BuildingEntity> List<S> findAll(Example<S> example, Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Page<BuildingEntity> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends BuildingEntity> S save(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<BuildingEntity> findById(Long id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public boolean existsById(Long id) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deleteById(Long id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(BuildingEntity entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends BuildingEntity> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public <S extends BuildingEntity> Optional<S> findOne(Example<S> example) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public <S extends BuildingEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends BuildingEntity> long count(Example<S> example) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public <S extends BuildingEntity> boolean exists(Example<S> example) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
//
//
