package com.wizardapp.model;

import java.io.Serializable;


public class ClassSubject implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private long createdAt;

	private String subjectType;

	private long updatedAt;

	public ClassSubject() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getSubjectType() {
		return this.subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
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