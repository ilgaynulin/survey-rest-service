package com.github.ilgaynulin.surveyrestservice.dto;

import javax.validation.constraints.NotNull;

public class EditQuestionDto {
    @NotNull
    private Long id;
    @NotNull
    private String questionText;
    @NotNull
    private Integer questionOrder;

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
}
