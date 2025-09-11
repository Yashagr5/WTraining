package com.company.dto;

public class OwnerNotifyDto {
private int ownerId;
private String ownerName;
public OwnerNotifyDto() {
	super();
	// TODO Auto-generated constructor stub
}
public OwnerNotifyDto(int ownerId, String ownerName) {
	super();
	this.ownerId = ownerId;
	this.ownerName = ownerName;
}
public int getOwnerId() {
	return ownerId;
}
public void setOwnerId(int ownerId) {
	this.ownerId = ownerId;
}
public String getOwnerName() {
	return ownerName;
}
public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
}

}
