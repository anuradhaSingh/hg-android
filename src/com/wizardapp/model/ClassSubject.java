package com.wizardapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class ClassSubject implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date createdAt;

	private String subjectType;

	private Timestamp updatedAt;

	private List<QuizQuestion> quizQuestions;

	public ClassSubject() {
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

	public String getSubjectType() {
		return this.subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
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
		quizQuestion.setClassSubject(this);

		return quizQuestion;
	}

	public QuizQuestion removeQuizQuestion(QuizQuestion quizQuestion) {
		getQuizQuestions().remove(quizQuestion);
		quizQuestion.setClassSubject(null);

		return quizQuestion;
	}

}