package com.onlinemarket.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class storeOwner {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	
	private Integer prodHistoryID;
	public Integer getProdHistoryID() {
		return prodHistoryID;
	}
	public void setProdHistoryID(Integer prodHistoryID) {
		this.prodHistoryID = prodHistoryID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Integer idOwner;
	private Integer idProduct;
	private Integer quantity;
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public storeOwner() {
		super();
		this.idOwner=0;
		this.idProduct=0;
		this.quantity=0;
	}
	public storeOwner(int idOwner, int idProduct,int quantity) {
		super();
		this.idOwner = idOwner;
		this.idProduct = idProduct;
		this.quantity=quantity;
	}
	public int getIdOwner() {
		return idOwner;
	}
	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	
	
}