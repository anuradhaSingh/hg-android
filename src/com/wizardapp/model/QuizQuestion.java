package com.wizardapp.model;

import java.io.Serializable;

public class QuizQuestion implements Serializable {

	private static final long serialVersionUID = 6347141501598406996L;

	private Integer id;

	private String answer;

	private long createdAt;

	private String createdBy;

	private byte[] description;

	private byte[] option1;

	private byte[] option2;

	private byte[] option3;

	private byte[] option4;

	private byte[] question;

	private byte status;

	private long updatedAt;

	private String uploadType;

	private ClassNumber classNumber;

	private ClassSubject classSubject;

	public QuizQuestion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public byte[] getDescription() {
		return this.description;
	}

	public void setDescription(byte[] description) {
		this.description = description;
	}

	public byte[] getOption1() {
		return this.option1;
	}

	public void setOption1(byte[] option1) {
		this.option1 = option1;
	}

	public byte[] getOption2() {
		return this.option2;
	}

	public void setOption2(byte[] option2) {
		this.option2 = option2;
	}

	public byte[] getOption3() {
		return this.option3;
	}

	public void setOption3(byte[] option3) {
		this.option3 = option3;
	}

	public byte[] getOption4() {
		return this.option4;
	}

	public void setOption4(byte[] option4) {
		this.option4 = option4;
	}

	public byte[] getQuestion() {
		return this.question;
	}

	public void setQuestion(byte[] question) {
		this.question = question;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getUploadType() {
		return this.uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public ClassNumber getClassNumber() {
		return this.classNumber;
	}

	public void setClassNumber(ClassNumber classNumber) {
		this.classNumber = classNumber;
	}

	public ClassSubject getClassSubject() {
		return this.classSubject;
	}

	public void setClassSubject(ClassSubject classSubject) {
		this.classSubject = classSubject;
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