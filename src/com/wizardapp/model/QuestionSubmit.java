package com.wizardapp.model;


public class QuestionSubmit {
	
	private Long id;

	private long createdAt;

	private boolean optedStatus;

	private long updatedAt;

	private String userSelectOption;

	private Long userId;

	private Long scholarshipId;

	private Long questionId;
	
	private String timeUsed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isOptedStatus() {
		return optedStatus;
	}

	public void setOptedStatus(boolean optedStatus) {
		this.optedStatus = optedStatus;
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

	public String getUserSelectOption() {
		return userSelectOption;
	}

	public void setUserSelectOption(String userSelectOption) {
		this.userSelectOption = userSelectOption;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getScholarshipId() {
		return scholarshipId;
	}

	public void setScholarshipId(Long scholarshipId) {
		this.scholarshipId = scholarshipId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getTimeUsed() {
		return timeUsed;
	}

	public void setTimeUsed(String timeUsed) {
		this.timeUsed = timeUsed;
	}
	
	
	
}
