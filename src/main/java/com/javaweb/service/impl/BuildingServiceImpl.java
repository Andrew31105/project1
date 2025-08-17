package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Override
	public List<BuildingDTO> getAllBuilding(String name, Long district) {
		List<BuildingEntity> buildingEntity = buildingRepository.getAllBuildings(name,district);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		
		for(BuildingEntity item : buildingEntity) {
			BuildingDTO building = new BuildingDTO();
			building.setName(item.getName());
			building.setAdress(item.getStreet() + "," + item.getWard());
			result.add(building);
		}
		
		return result;
	}

}