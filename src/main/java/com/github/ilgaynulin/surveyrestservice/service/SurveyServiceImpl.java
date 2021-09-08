package com.github.ilgaynulin.surveyrestservice.service;

import com.github.ilgaynulin.surveyrestservice.dto.SurveyEntryDto;
import com.github.ilgaynulin.surveyrestservice.dto.SurveyUpdateDto;
import com.github.ilgaynulin.surveyrestservice.model.Survey;
import com.github.ilgaynulin.surveyrestservice.repository.SearchCriteria;
import com.github.ilgaynulin.surveyrestservice.repository.SurveyRepository;
import com.github.ilgaynulin.surveyrestservice.repository.SurveySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class SurveyServiceImpl implements SurveyService {
    SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public Page<Survey> getAll(int pageNum, int pageSize, String sortBy, Queue<SearchCriteria> searchCriteria) {
        if(searchCriteria.isEmpty()) {
            return surveyRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(sortBy).descending()));
        }

        Specification spec = Specification.where(new SurveySpecification(searchCriteria.poll()));
        while(!searchCriteria.isEmpty()) {
            spec.and(new SurveySpecification(searchCriteria.poll()));
        }

        return surveyRepository.findAll(spec, PageRequest.of(pageNum, pageSize, Sort.by(sortBy).descending()));
    }

    @Override
    public Survey update(SurveyUpdateDto updateDto) {
        if(surveyRepository.findById(updateDto.getId()) == null) {
            return null;
        }
        Survey savedSurvey = surveyRepository.save(updateDto.toSurvey());
        return savedSurvey;
    }

    @Override
    public Survey add(SurveyEntryDto entryDto) {
        Survey savedSurvey = surveyRepository.save(entryDto.toSurvey());
        return savedSurvey;
    }

    @Override
    public void delete(Long id) {
        surveyRepository.deleteById(id);
    }
}