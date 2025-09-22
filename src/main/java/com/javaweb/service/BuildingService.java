package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.Model.BuildingRequestBodyDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(Map<String,Object> params, List<String> typeCode);
}
