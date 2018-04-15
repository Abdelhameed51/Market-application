package com.onlinemarket.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Collaborator {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	public Integer getIdOwner() {
		return idOwner;
	}
	public void setIdOwner(Integer idOwner) {
		this.idOwner = idOwner;
	}
	public Integer getIdCollaborator() {
		return idCollaborator;
	}
	public void setIdCollaborator(Integer idCollaborator) {
		this.idCollaborator = idCollaborator;
	}
	public Collaborator(Integer idOwner, Integer idCollaborator) {
		super();
		this.idOwner = idOwner;
		this.idCollaborator = idCollaborator;
	}
	public Collaborator() {
		super();
		this.idOwner = -1;
		this.idCollaborator = -1;
	}
	private Integer idOwner;
	private Integer idCollaborator;
}
