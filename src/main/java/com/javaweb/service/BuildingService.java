package com.javaweb.service;

import java.util.List;

import com.javaweb.Model.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> getAllBuilding(String name, Long district);

}
