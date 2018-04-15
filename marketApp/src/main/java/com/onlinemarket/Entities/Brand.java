package com.onlinemarket.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Brand {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Integer id;
	
	
	
	private String brandName ;
	private String brandCategory;
	public Brand() {
		super();
		this.brandName = "";
		this.brandCategory = "";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandCategory() {
		return brandCategory;
	}
	public void setBrandCategory(String brandCategory) {
		this.brandCategory = brandCategory;
	}
	public Brand(Integer id, String brandName, String brandCategory) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.brandCategory = brandCategory;
	}
	

}
