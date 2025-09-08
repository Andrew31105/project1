package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {

	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = new BuildingDTO();
		building.setName(item.getName());
		DistrictEntity districtEntity = districtRepository.findNameBy(item.getDistrictId());
		List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getId());
		building.setAdress(item.getStreet() + "," + item.getWard() +"," + districtEntity.getName());
		building.setName_Manager(item.getManagerName());
		building.setNumberOfBasement(item.getNumberOfBasement());
		building.setPhoneNumberManager(item.getManagerPhoneNumbers());
		building.setRentPrice(item.getRentPrice());
		building.setAreaFloor(item.getFloorArea());
		String areaResults = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResults);
		building.setTypeFloor(item.getTypeFloor());
		return building;
	}
}
