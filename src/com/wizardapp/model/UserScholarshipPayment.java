package com.wizardapp.model;

import java.io.Serializable;


public class UserScholarshipPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String apiResponse;

	private String apiTransactionId;

	private long createdAt;

	private String mode;

	private String paymentComment;

	private String paymentStatus;

	private long updatedAt;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiResponse() {
		return this.apiResponse;
	}

	public void setApiResponse(String apiResponse) {
		this.apiResponse = apiResponse;
	}

	public String getApiTransactionId() {
		return this.apiTransactionId;
	}

	public void setApiTransactionId(String apiTransactionId) {
		this.apiTransactionId = apiTransactionId;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getPaymentComment() {
		return this.paymentComment;
	}

	public void setPaymentComment(String paymentComment) {
		this.paymentComment = paymentComment;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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