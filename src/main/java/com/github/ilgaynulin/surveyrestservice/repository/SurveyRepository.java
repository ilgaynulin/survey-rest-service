package com.github.ilgaynulin.surveyrestservice.repository;

import com.github.ilgaynulin.surveyrestservice.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {
}
