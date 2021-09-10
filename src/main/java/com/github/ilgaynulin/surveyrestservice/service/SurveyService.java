package com.github.ilgaynulin.surveyrestservice.service;

import com.github.ilgaynulin.surveyrestservice.dto.SurveyEntryDto;
import com.github.ilgaynulin.surveyrestservice.dto.SurveyUpdateDto;
import com.github.ilgaynulin.surveyrestservice.model.Survey;
import com.github.ilgaynulin.surveyrestservice.repository.SearchCriteria;
import org.springframework.data.domain.Page;
import java.util.Queue;

public interface SurveyService {
    /**
     * The method allows you to get a page presentation of surveys from database.
     * @param pageNum which page should return method
     * @param pageSize number of questions in page
     * @param sortBy the field by which the page will be sorted
     * @param searchCriteria (optional) criteria which will be applied to search
     * @return sorted page presentation of questions
     */
    public Page<Survey> getAll(int pageNum, int pageSize, String sortBy, Queue<SearchCriteria> searchCriteria);

    /**
     * @return saved survey in database
     */
    public Survey update(SurveyUpdateDto survey);

    /**
     * @return saved survey in database
     */
    public Survey add(SurveyEntryDto entryDto);
    public void delete(Long id);
}
