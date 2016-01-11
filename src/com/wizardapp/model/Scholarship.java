package com.wizardapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class Scholarship implements Serializable {

	private static final long serialVersionUID = 3915442120008656417L;

	private Integer id;

	private Date createdAt;

	private String createdBy;

	private String endDate;

	private String prizeMoney;

	private String prizeMonth;

	private String prizeYear;

	private String scholarshipName;

	private byte[] scholarshipSubject;

	private String startDate;

	private Timestamp updatedAt;

	private ClassNumber classNumber;

	public Scholarship() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public byte[] getScholarshipSubject() {
		return this.scholarshipSubject;
	}

	public void setScholarshipSubject(byte[] scholarshipSubject) {
		this.scholarshipSubject = scholarshipSubject;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ClassNumber getClassNumber() {
		return this.classNumber;
	}

	public void setClassNumber(ClassNumber classNumber) {
		this.classNumber = classNumber;
	}

}