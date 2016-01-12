package com.wizardapp.model;

import java.io.Serializable;


public class ClassNumber implements Serializable {
	private static final long serialVersionUID = 8378653576920431340L;

	private Integer id;

	private String classType;

	private long createdAt;

	private long updatedAt;

	public ClassNumber() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassType() {
		return this.classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

}