package com.onlinemarket.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Buyer {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private Integer idBuyer;
	private String address;
	private Integer amounts;
	public Buyer() {
		super();
		this.idBuyer = 0;
		this.address ="";
		this.amounts = 0;
		this.idProduct = 0;
	}
	public Buyer(Integer idBuyer, String address, Integer amounts, Integer idProduct) {
		super();
		this.idBuyer = idBuyer;
		this.address = address;
		this.amounts = amounts;
		this.idProduct = idProduct;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdBuyer() {
		return idBuyer;
	}
	public void setIdBuyer(Integer idBuyer) {
		this.idBuyer = idBuyer;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getAmounts() {
		return amounts;
	}
	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	private Integer idProduct;
	
	
}
