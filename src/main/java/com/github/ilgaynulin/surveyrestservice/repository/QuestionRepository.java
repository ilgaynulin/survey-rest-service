package com.github.ilgaynulin.surveyrestservice.repository;

import com.github.ilgaynulin.surveyrestservice.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query( "select q from question q where q.survey.id = :surveyId" )
    Page<Question> findAllBySurveyId(@Param("surveyId") Long surveyId, Pageable pageRequest);
    @Query( "select q from question q where q.survey.id = :surveyId" )
    List<Question> findAllBySurveyId(@Param("surveyId") Long surveyId);
}
