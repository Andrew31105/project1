package com.javaweb.converter;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.check.MapUtils;

@Component
public class BuildingSearchBuilderConverter {
	public 	BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params, List<String> typeCode) {
		
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
				.setName(MapUtils.getObject(params, "name", String.class))
	            .setFloorArea(MapUtils.getObject(params, "floorArea", Integer.class))
	            .setWard(MapUtils.getObject(params, "ward", String.class))
	            .setStreet(MapUtils.getObject(params, "street", String.class))
	            .setDistrictCode(MapUtils.getObject(params, "DistrictId", String.class))
	            .setNumberOfBasement(MapUtils.getObject(params, "numberofbasement", Integer.class))
	            .setTypeCode(typeCode)
	            .setManagerName(MapUtils.getObject(params, "managername", String.class))
	            .setManagerPhoneNumber(MapUtils.getObject(params, "managerphonenumber", String.class))
	            .setRentPriceTo(MapUtils.getObject(params, "rentpriceto", Integer.class))
	            .setRentPriceFrom(MapUtils.getObject(params, "rentpricefrom", Integer.class))
	            .setAreaFrom(MapUtils.getObject(params, "areafrom", Integer.class))
	            .setAreaTo(MapUtils.getObject(params, "areato", Integer.class))
	            .setStaffId(MapUtils.getObject(params, "staffid", Integer.class))
	            .build();
		return buildingSearchBuilder;
	}
}
