package com.javaweb.api;


import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.Model.BuildingRequestBodyDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;



@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	@RequestMapping(value="/api/building", method = RequestMethod.GET)
		public List<BuildingDTO> Get_building(@RequestParam Map<String,Object> params,
				@RequestParam List<String> typeCode) {
			List<BuildingDTO> result = buildingService.findAll(params, typeCode);
			return result;
		}
	
	@RequestMapping(value="/api/building/{name}/{street}", method = RequestMethod.GET)
	public BuildingDTO getBuildingById(@PathVariable String name, @PathVariable String street) {
		BuildingDTO result  = new BuildingDTO();
		List<BuildingEntity> building = buildingRepository.findByNameContainingAndStreet(name,street); 
		return result;
	}
	
	@PostMapping(value="/api/building")
		public void createBuilding(@RequestBody BuildingRequestBodyDTO buildingRequestDTO) {
		BuildingEntity builEntity = new BuildingEntity();
		builEntity.setName(buildingRequestDTO.getName());
	    builEntity.setStreet(buildingRequestDTO.getStreet());
	    builEntity.setWard(buildingRequestDTO.getWard());
	    builEntity.setNumberOfBasement(buildingRequestDTO.getNumberOfBasement());
	    DistrictEntity districtEntity = new DistrictEntity();
	    districtEntity.setId(buildingRequestDTO.getDistrictId());
	    builEntity.setDistrict(districtEntity);
	    buildingRepository.save(builEntity);
	    System.out.print("ok");
	}
	
	@PutMapping(value = "/api/building")
	public void updateBuilding(@RequestBody BuildingRequestBodyDTO buildingRequestDTO) {
	    BuildingEntity builEntity = buildingRepository.findById(buildingRequestDTO.getId()).get();
	    builEntity.setName(buildingRequestDTO.getName());
	    builEntity.setStreet(buildingRequestDTO.getStreet());
	    builEntity.setWard(buildingRequestDTO.getWard());
	    builEntity.setNumberOfBasement(buildingRequestDTO.getNumberOfBasement());
	    DistrictEntity districtEntity = new DistrictEntity();
	    districtEntity.setId(buildingRequestDTO.getDistrictId());
	    builEntity.setDistrict(districtEntity);
	    buildingRepository.save(builEntity);
	    System.out.print("ok");
	}
	
	@DeleteMapping(value = "/api/building/{ids}")
	public void deleteBuilding(@PathVariable Integer[] ids) {
	    buildingRepository.deleteByIdIn(ids);
	}

	
}	