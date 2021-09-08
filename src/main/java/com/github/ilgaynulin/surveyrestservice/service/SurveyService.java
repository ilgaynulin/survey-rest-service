package com.github.ilgaynulin.surveyrestservice.service;

import com.github.ilgaynulin.surveyrestservice.dto.SurveyEntryDto;
import com.github.ilgaynulin.surveyrestservice.dto.SurveyUpdateDto;
import com.github.ilgaynulin.surveyrestservice.model.Survey;
import com.github.ilgaynulin.surveyrestservice.repository.SearchCriteria;
import org.springframework.data.domain.Page;
import java.util.Queue;

public interface SurveyService {
    public Page<Survey> getAll(int pageNum, int pageSize, String sortBy, Queue<SearchCriteria> searchCriteria);
    public Survey update(SurveyUpdateDto survey);
    public Survey add(SurveyEntryDto entryDto);
    public void delete(Long id);
}
