package com.github.ilgaynulin.surveyrestservice.rest;

import com.github.ilgaynulin.surveyrestservice.dto.EditQuestionDto;
import com.github.ilgaynulin.surveyrestservice.dto.QuestionEntryDto;
import com.github.ilgaynulin.surveyrestservice.model.Question;
import com.github.ilgaynulin.surveyrestservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionRestController {
    QuestionService questionService;

    @Autowired
    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<Page<Question>> getQuestions(
            @RequestParam(defaultValue = "questionOrder") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam Optional<Long> surveyId) {

        Page<Question> result = questionService.getQuestionsBySurveyId(pageNum, pageSize, sortBy, surveyId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(
            @RequestBody @Valid QuestionEntryDto questionEntryDto) {
        Question savedQuestion = questionService.addQuestion(questionEntryDto);
        return new ResponseEntity<Question>(savedQuestion, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Question> updateQuestion(
            @RequestBody @Valid EditQuestionDto editQuestionDto) {
        Question updatedQuestion = questionService.updateQuestion(editQuestionDto);

        return new ResponseEntity<Question>(updatedQuestion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(
            @PathVariable(value = "id") Long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
