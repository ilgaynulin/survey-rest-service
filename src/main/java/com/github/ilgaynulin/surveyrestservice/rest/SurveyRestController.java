package com.github.ilgaynulin.surveyrestservice.rest;

import com.github.ilgaynulin.surveyrestservice.dto.SurveyEntryDto;
import com.github.ilgaynulin.surveyrestservice.dto.SurveyUpdateDto;
import com.github.ilgaynulin.surveyrestservice.model.Survey;
import com.github.ilgaynulin.surveyrestservice.repository.SearchCriteria;
import com.github.ilgaynulin.surveyrestservice.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/surveys")
public class SurveyRestController {
    private SurveyService surveyService;

    @Autowired
    public SurveyRestController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public ResponseEntity<Page<Survey>> getAllSurveys(
            @RequestParam String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String name
            ) {
        Queue<SearchCriteria> criteria = new LinkedList<>();

        if(startDate != null) {
            criteria.offer(new SearchCriteria("startDate", "during", startDate));
        }
        if(active != null)
            criteria.offer(new SearchCriteria("active", "equals", active));
        if(name != null)
            criteria.offer(new SearchCriteria("name", "equals", name));

        Page<Survey> surveys = surveyService.getAll(pageNum, pageSize, sortBy, criteria);

        return new ResponseEntity<Page<Survey>>(surveys, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Survey> addSurvey(
            @RequestBody @Valid SurveyEntryDto surveyEntryDto) {
        Survey savedSurvey = surveyService.add(surveyEntryDto);
        return new ResponseEntity<>(savedSurvey, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Survey> updateSurvey(
            @RequestBody @Valid SurveyUpdateDto surveyUpdateDto) {
        Survey updatedSurvey = surveyService.update(surveyUpdateDto);
        return new ResponseEntity<>(updatedSurvey, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Survey> deleteSurvey(
            @PathVariable(value = "id") Long id) {
        surveyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
