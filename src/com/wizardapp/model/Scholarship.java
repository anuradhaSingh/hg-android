package com.wizardapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the scholarship database table.
 * 
 */

public class Scholarship implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String announcementDate;

	private long createdAt;

	

	private String createdBy;
	
	private String[] noOfQuestion_perSubject;

	private String prizeMoney;

	private String registrationEndDate;

	private String registrationStartDate;

	private String scholarshipEndDate;

	private String scholarshipFees;

	private String scholarshipName;

	private String scholarshipStartDate;

	private String[] scholarshipSubject;

	private String testTimer;

	private int totalQuestion;

	private long updatedAt;

	
	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	private ClassNumber classNumber;

	public Scholarship() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnnouncementDate() {
		return this.announcementDate;
	}

	public void setAnnouncementDate(String announcementDate) {
		this.announcementDate = announcementDate;
	}

	

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getPrizeMoney() {
		return this.prizeMoney;
	}

	public void setPrizeMoney(String prizeMoney) {
		this.prizeMoney = prizeMoney;
	}

	public String getRegistrationEndDate() {
		return this.registrationEndDate;
	}

	public void setRegistrationEndDate(String registrationEndDate) {
		this.registrationEndDate = registrationEndDate;
	}

	public String getRegistrationStartDate() {
		return this.registrationStartDate;
	}

	public void setRegistrationStartDate(String registrationStartDate) {
		this.registrationStartDate = registrationStartDate;
	}

	public String getScholarshipEndDate() {
		return this.scholarshipEndDate;
	}

	public void setScholarshipEndDate(String scholarshipEndDate) {
		this.scholarshipEndDate = scholarshipEndDate;
	}

	public String getScholarshipFees() {
		return this.scholarshipFees;
	}

	public void setScholarshipFees(String scholarshipFees) {
		this.scholarshipFees = scholarshipFees;
	}

	public String getScholarshipName() {
		return this.scholarshipName;
	}

	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}

	public String getScholarshipStartDate() {
		return this.scholarshipStartDate;
	}

	public void setScholarshipStartDate(String scholarshipStartDate) {
		this.scholarshipStartDate = scholarshipStartDate;
	}


	public String getTestTimer() {
		return this.testTimer;
	}

	public void setTestTimer(String testTimer) {
		this.testTimer = testTimer;
	}

	public int getTotalQuestion() {
		return this.totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	
	public ClassNumber getClassNumber() {
		return this.classNumber;
	}

	public void setClassNumber(ClassNumber classNumber) {
		this.classNumber = classNumber;
	}

	public String[] getNoOfQuestion_perSubject() {
		return noOfQuestion_perSubject;
	}

	public void setNoOfQuestion_perSubject(String[] noOfQuestion_perSubject) {
		this.noOfQuestion_perSubject = noOfQuestion_perSubject;
	}

	public String[] getScholarshipSubject() {
		return scholarshipSubject;
	}

	public void setScholarshipSubject(String[] scholarshipSubject) {
		this.scholarshipSubject = scholarshipSubject;
	}
	
	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
}