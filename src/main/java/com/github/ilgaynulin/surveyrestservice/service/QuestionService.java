package com.github.ilgaynulin.surveyrestservice.service;

import com.github.ilgaynulin.surveyrestservice.dto.EditQuestionDto;
import com.github.ilgaynulin.surveyrestservice.dto.QuestionEntryDto;
import com.github.ilgaynulin.surveyrestservice.model.Question;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface QuestionService {
    /**
     * The method allows you to get a page presentation of questions from database.
     * @param pageNum which page should return method
     * @param pageSize number of questions in page
     * @param sortBy the field by which the page will be sorted
     * @param surveyId (optional) if survey id provided method will return questions from that survey,
     *                 otherwise will return all questions from database
     * @return sorted page presentation of questions
     */
    public Page<Question> getQuestionsBySurveyId(int pageNum, int pageSize, String sortBy, Optional<Long> surveyId);

    /**
     * @return saved question in database
     */
    public Question addQuestion(QuestionEntryDto questionEntryDto);

    public void deleteQuestion(Long questionId);

    /**
     * @return saved question in database
     */
    public Question updateQuestion(EditQuestionDto question);
}
