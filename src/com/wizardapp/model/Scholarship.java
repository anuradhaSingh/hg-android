package com.wizardapp.model;

import java.io.Serializable;


public class Scholarship implements Serializable {

	private static final long serialVersionUID = 3915442120008656417L;

	private Integer id;

	private long createdAt;

	private String createdBy;

	private String endDate;

	private String prizeMoney;

	private String prizeMonth;

	private String prizeYear;

	private String scholarshipName;

	private String scholarshipSubject;

	private String startDate;

	private long updatedAt;

	private ClassNumber classNumber;

	public Scholarship() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPrizeMoney() {
		return this.prizeMoney;
	}

	public void setPrizeMoney(String prizeMoney) {
		this.prizeMoney = prizeMoney;
	}

	public String getPrizeMonth() {
		return this.prizeMonth;
	}

	public void setPrizeMonth(String prizeMonth) {
		this.prizeMonth = prizeMonth;
	}

	public String getPrizeYear() {
		return this.prizeYear;
	}

	public void setPrizeYear(String prizeYear) {
		this.prizeYear = prizeYear;
	}

	public String getScholarshipName() {
		return this.scholarshipName;
	}

	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
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

	public ClassNumber getClassNumber() {
		return this.classNumber;
	}

	public void setClassNumber(ClassNumber classNumber) {
		this.classNumber = classNumber;
	}

	public String getScholarshipSubject() {
		return scholarshipSubject;
	}

	public void setScholarshipSubject(String scholarshipSubject) {
		this.scholarshipSubject = scholarshipSubject;
	}

}