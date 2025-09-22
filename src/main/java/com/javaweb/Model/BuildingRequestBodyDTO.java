package com.javaweb.Model;

public class BuildingRequestBodyDTO {
	private Integer id;
	private String name;
	private Integer numberOfBasement;
	private String ManagerName;
	private String ManagerPhoneNumbers;
	private Integer FloorArea;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
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
	public Integer getFloorArea() {
		return FloorArea;
	}
	public void setFloorArea(Integer floorArea) {
		FloorArea = floorArea;
	}
	public Integer getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getBrokeRageFee() {
		return BrokeRageFee;
	}
	public void setBrokeRageFee(Integer brokeRageFee) {
		BrokeRageFee = brokeRageFee;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	public Integer getDistrictId() {
		return DistrictId;
	}
	public void setDistrictId(Integer districtId) {
		DistrictId = districtId;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Integer rentPrice;
	private String level;
	private Integer BrokeRageFee;
	private String rentArea;
	private Integer DistrictId;
	private String ward;
	private String Street;
}
