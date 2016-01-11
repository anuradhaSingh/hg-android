package com.wizardapp.model;

import java.io.Serializable;


public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int roleId;

	private String type;

	public RolePermission() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}