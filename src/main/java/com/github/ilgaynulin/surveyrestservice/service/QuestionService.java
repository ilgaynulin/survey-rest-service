package com.github.ilgaynulin.surveyrestservice.service;

import com.github.ilgaynulin.surveyrestservice.dto.EditQuestionDto;
import com.github.ilgaynulin.surveyrestservice.dto.QuestionEntryDto;
import com.github.ilgaynulin.surveyrestservice.model.Question;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface QuestionService {
    public Page<Question> getQuestionsBySurveyId(int pageNum, int pageSize, String sortBy, Optional<Long> surveyId);
    public Question addQuestion(QuestionEntryDto questionEntryDto);
    public void deleteQuestion(Long questionId);
    public Question saveQuestion(EditQuestionDto question);
}
