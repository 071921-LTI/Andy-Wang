package com.lti.models;

import java.io.Serializable;

public class Shoes implements Serializable{
	private int id;
	private String brand; //Nike, Adidas, Vans, Converse, Under Armour, New Balance
	private int size;
	private String shoeType; //sandals, sneakers, slides, cleats
	private String color; //red, blue, green, orange, black, white
	private double price;
//	private ArrayList<User> bidList = new ArrayList<>();
//	
//	public ArrayList<User> getBidList(){
//		return this.bidList;
//	}
	
	


	public Shoes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Shoes(int id, String brand, int size, String shoe_type, String color, double shoe_price) {
		super();
		this.id = id;
		this.brand = brand;
		this.size = size;
		this.shoeType = shoe_type;
		this.color = color;
		this.price = shoe_price;
	}
	
	public Shoes(int id) {
		super();
		this.id = id;
	}
	
	public Shoes(String brand, int size, String shoe_type, String color, double shoe_price) {
		super();
		this.brand = brand;
		this.size = size;
		this.shoeType = shoe_type;
		this.color = color;
		this.price = shoe_price;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getShoeType() {
		return shoeType;
	}
	public void setShoeType(String shoeType) {
		this.shoeType = shoeType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Shoe id=" + id + ", brand=" + brand + ", size=" + size + ", shoeType=" + shoeType + ", color=" + color
				+ ", price=" + price;
//		return (id + " " + brand + " " + size + " " +  shoeType + " " + color + " " + price);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((shoeType == null) ? 0 : shoeType.hashCode());
		result = prime * result + size;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shoes other = (Shoes) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (shoeType == null) {
			if (other.shoeType != null)
				return false;
		} else if (!shoeType.equals(other.shoeType))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	
	
}
