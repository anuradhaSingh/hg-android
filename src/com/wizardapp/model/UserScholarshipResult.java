package com.wizardapp.model;

import java.io.Serializable;


/**
 * The persistent class for the user_scholarship_result database table.
 * 
 */
public class UserScholarshipResult implements Serializable {
	private static final long serialVersionUID = 1L;

private Long id;

	private boolean awardStatus;

	private long createdAt;

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	private int currectQuestion;

	private double percentage;

	private int rank;

	private int unanswered;

	private long updatedAt;

	private boolean winStatus;
    private int wrongQuestion;

	private UserDetail userDetail;

	//bi-directional many-to-one association to Scholarship
	private Scholarship scholarship;

	public UserScholarshipResult() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public int getCurrectQuestion() {
		return this.currectQuestion;
	}

	public void setCurrectQuestion(int currectQuestion) {
		this.currectQuestion = currectQuestion;
	}

	
	public int getRank() {
		return this.rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getUnanswered() {
		return this.unanswered;
	}

	public void setUnanswered(int unanswered) {
		this.unanswered = unanswered;
	}

	
	public int getWrongQuestion() {
		return this.wrongQuestion;
	}

	public void setWrongQuestion(int wrongQuestion) {
		this.wrongQuestion = wrongQuestion;
	}

	
	
	public boolean isAwardStatus() {
		return awardStatus;
	}

	public void setAwardStatus(boolean awardStatus) {
		this.awardStatus = awardStatus;
	}

	public boolean isWinStatus() {
		return winStatus;
	}

	public void setWinStatus(boolean winStatus) {
		this.winStatus = winStatus;
	}

}