package com.github.ilgaynulin.surveyrestservice.dto;

import com.github.ilgaynulin.surveyrestservice.model.Question;

public class EditQuestionDto {
    private Long id;
    private String questionText;
    private Integer questionOrder;
    private Long surveyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Question toQuestion() {
        Question question = new Question();

        question.setId(this.id);
        question.setQuestionText(this.questionText);
        question.setQuestionOrder(this.questionOrder);

        return question;
    }
}
