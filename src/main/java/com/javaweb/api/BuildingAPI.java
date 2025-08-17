package com.javaweb.api;


import java.util.List;

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
		public List<BuildingDTO> Get_building(@RequestParam(name = "name", required = false) String name,
				@RequestParam(name = "district", required = false) Long district  ) {
			List<BuildingDTO> result = buildingService.getAllBuilding(name, district);
			return result;
		}
	
}	