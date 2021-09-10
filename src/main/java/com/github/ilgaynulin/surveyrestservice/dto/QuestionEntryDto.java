package com.github.ilgaynulin.surveyrestservice.dto;

import javax.validation.constraints.NotNull;

public class QuestionEntryDto {
    @NotNull
    String questionText;
    @NotNull
    Long surveyId;

    public QuestionEntryDto() {
    }

    public QuestionEntryDto(String questionText, Long surveyId) {
        this.questionText = questionText;
        this.surveyId = surveyId;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
