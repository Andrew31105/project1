package com.javaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.Model.BuildingRequestBodyDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;

@Component
public class BuildingEntityConverter {
		
	@Autowired
	private ModelMapper modelMapper;
		
		public BuildingEntity buildingEntityConverter(BuildingRequestBodyDTO getItem) {
			BuildingEntity Entity = new BuildingEntity();
			Entity.setName(getItem.getName());
			Entity.setWard(getItem.getWard());
			Entity.setStreet(getItem.getStreet());
			Entity.setNumberOfBasement(getItem.getNumberOfBasement());
			DistrictEntity	districtEntity = new DistrictEntity();
			districtEntity.setId(getItem.getDistrictId());
			Entity.setDistrict(districtEntity);
			return Entity;
		}
}
