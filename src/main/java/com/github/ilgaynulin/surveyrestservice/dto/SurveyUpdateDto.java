package com.github.ilgaynulin.surveyrestservice.dto;

import com.github.ilgaynulin.surveyrestservice.model.Survey;

import java.util.Date;

public class SurveyUpdateDto {
    private Long id;
    private String name;
    private Date startDate;
    private Date finishDate;
    private boolean active;

    public Survey toSurvey() {
        Survey survey = new Survey();

        survey.setId(this.id);
        survey.setName(this.name);
        survey.setStartDate(this.startDate);
        survey.setFinishDate(this.finishDate);
        survey.setActive(this.active);

        return survey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
