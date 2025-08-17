package com.menu;

public class menuItem {
	private String name;
    private String description;
    private String category;
    private double price;
    private String availability;
    
    public menuItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    public menuItem(String name, String description, String category, double price, String availability) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.availability = availability;
	}
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
    
    
}
