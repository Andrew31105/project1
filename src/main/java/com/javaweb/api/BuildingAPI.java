package com.javaweb.api;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.service.BuildingService;



@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	@RequestMapping(value="/api/building", method = RequestMethod.GET)
		public List<BuildingDTO> Get_building(@RequestParam Map<String,Object> params,
				@RequestParam List<String> typeCode) {
			List<BuildingDTO> result = buildingService.findAll(params, typeCode);
			return result;
		}
	
}	