package com.onlinemarket.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private Integer NumberOfProducts;
	public Integer getNumberOfProducts() {
		return NumberOfProducts;
	}
	public void setNumberOfProducts(Integer numberOfProducts) {
		NumberOfProducts = numberOfProducts;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Integer counter;
	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	private String name;
	private String price;
	private String category;
	private String brandName ;
	public Product() {
		this.name = "";
		this.price = "";
		this.category = "";
		this.brandName = "";
		this.counter=0;
		this.NumberOfProducts=1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Product(String name, String price, String category, String brandName,int counter,int NumberOfProducts) {
		super();
		this.name = name;
		this.counter=counter;
		this.price = price;
		this.category = category;
		this.brandName = brandName;
		this.NumberOfProducts=NumberOfProducts;
	}
	
}
