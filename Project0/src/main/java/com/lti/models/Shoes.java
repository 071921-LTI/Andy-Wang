package com.lti.models;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Shoes implements Serializable{
	private String brand; //Nike, Adidas, Vans, Converse, Under Armour, New Balance
	private int size;
	private int shoeType; //sandals, sneakers, slides, cleats
	private String color; //red, blue, green, orange, black, white
	private int price;
	private ArrayList<User> bidList = new ArrayList<>();
	
	public ArrayList<User> getBidList(){
		return this.bidList;
	}
	public String getBrand() {
		return this.brand;
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
	public int getShoeType() {
		return shoeType;
	}
	public void setShoeType(int shoeType) {
		this.shoeType = shoeType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Shoes [brand=" + brand + ", size=" + size + ", shoeType=" + shoeType + ", color=" + color + ", price="
				+ price + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + price;
		result = prime * result + shoeType;
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
		if (price != other.price)
			return false;
		if (shoeType != other.shoeType)
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	
	
}
