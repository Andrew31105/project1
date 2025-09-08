package com.javaweb.repository.entity;

public class BuildingEntity {
	private Integer Id;
	private String Name;
	private Integer NumberOfBasement;
	private String Ward;
	private Integer DistrictId;
	private String Street;
	private Integer FloorArea;
	private Integer RentPrice;
	private Integer ServiceFee;
	private Integer BrokeRangeFee;
	private String ManagerName;
	private String ManagerPhoneNumbers;
	private String TypeFloor;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getNumberOfBasement() {
		return NumberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		NumberOfBasement = numberOfBasement;
	}
	public String getWard() {
		return Ward;
	}
	public void setWard(String ward) {
		Ward = ward;
	}
	public Integer getDistrictId() {
		return DistrictId;
	}
	public void setDistrictId(Integer districtId) {
		DistrictId = districtId;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public Integer getFloorArea() {
		return FloorArea;
	}
	public void setFloorArea(Integer floorArea) {
		FloorArea = floorArea;
	}
	public Integer getRentPrice() {
		return RentPrice;
	}
	public void setRentPrice(Integer rentPrice) {
		RentPrice = rentPrice;
	}
	public Integer getServiceFee() {
		return ServiceFee;
	}
	public void setServiceFee(Integer serviceFee) {
		ServiceFee = serviceFee;
	}
	public Integer getBrokeRangeFee() {
		return BrokeRangeFee;
	}
	public void setBrokeRangeFee(Integer brokeRangeFee) {
		BrokeRangeFee = brokeRangeFee;
	}
	public String getManagerName() {
		return ManagerName;
	}
	public void setManagerName(String managerName) {
		ManagerName = managerName;
	}
	public String getManagerPhoneNumbers() {
		return ManagerPhoneNumbers;
	}
	public void setManagerPhoneNumbers(String managerPhoneNumbers) {
		ManagerPhoneNumbers = managerPhoneNumbers;
	}
	public String getTypeFloor() {
		return TypeFloor;
	}
	public void setTypeFloor(String typeFloor) {
		TypeFloor = typeFloor;
	}
	
}