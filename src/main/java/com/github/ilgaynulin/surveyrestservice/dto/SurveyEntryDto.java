package com.github.ilgaynulin.surveyrestservice.dto;

import com.github.ilgaynulin.surveyrestservice.model.Survey;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SurveyEntryDto {
    @NotNull
    private String name;
    @NotNull
    private Date startDate;
    @NotNull
    private Date finishDate;
    @NotNull
    private boolean active;

    public Survey toSurvey() {
        Survey survey = new Survey();

        survey.setName(this.name);
        survey.setStartDate(this.startDate);
        survey.setFinishDate(this.finishDate);
        survey.setActive(this.active);

        return survey;
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
