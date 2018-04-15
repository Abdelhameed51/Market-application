package com.onlinemarket.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StoreProductHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreOwnerId() {
		return storeOwnerId;
	}
	public void setStoreOwnerId(Integer storeOwnerId) {
		this.storeOwnerId = storeOwnerId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getPreviousAmount() {
		return previousAmount;
	}
	public void setPreviousAmount(Integer previousAmount) {
		this.previousAmount = previousAmount;
	}
	public Integer getNextAmount() {
		return nextAmount;
	}
	public void setNextAmount(Integer nextAmount) {
		this.nextAmount = nextAmount;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	private Integer storeOwnerId;
	private Integer productId;
	private Integer previousAmount;
	private Integer nextAmount;
	private String action;
	public StoreProductHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
}
