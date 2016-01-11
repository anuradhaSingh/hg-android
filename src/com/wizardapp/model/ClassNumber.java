package com.wizardapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class ClassNumber implements Serializable {
	private static final long serialVersionUID = 8378653576920431340L;

	private Integer id;

	private String classType;

	private Date createdAt;

	private Timestamp updatedAt;

	private List<QuizQuestion> quizQuestions;

	private List<Scholarship> scholarships;

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

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<QuizQuestion> getQuizQuestions() {
		return this.quizQuestions;
	}

	public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}

	public QuizQuestion addQuizQuestion(QuizQuestion quizQuestion) {
		getQuizQuestions().add(quizQuestion);
		quizQuestion.setClassNumber(this);

		return quizQuestion;
	}

	public QuizQuestion removeQuizQuestion(QuizQuestion quizQuestion) {
		getQuizQuestions().remove(quizQuestion);
		quizQuestion.setClassNumber(null);

		return quizQuestion;
	}

	public List<Scholarship> getScholarships() {
		return this.scholarships;
	}

	public void setScholarships(List<Scholarship> scholarships) {
		this.scholarships = scholarships;
	}

	public Scholarship addScholarship(Scholarship scholarship) {
		getScholarships().add(scholarship);
		scholarship.setClassNumber(this);

		return scholarship;
	}

	public Scholarship removeScholarship(Scholarship scholarship) {
		getScholarships().remove(scholarship);
		scholarship.setClassNumber(null);

		return scholarship;
	}

}