package com.github.ilgaynulin.surveyrestservice.service;

import com.github.ilgaynulin.surveyrestservice.dto.EditQuestionDto;
import com.github.ilgaynulin.surveyrestservice.dto.QuestionEntryDto;
import com.github.ilgaynulin.surveyrestservice.model.Question;
import com.github.ilgaynulin.surveyrestservice.model.Survey;
import com.github.ilgaynulin.surveyrestservice.repository.QuestionRepository;
import com.github.ilgaynulin.surveyrestservice.repository.SurveyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceImplTest {
    QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
    SurveyRepository surveyRepository = Mockito.mock(SurveyRepository.class);

    QuestionService questionService;

    @BeforeEach
    public void initService() { questionService = new QuestionServiceImpl(questionRepository, surveyRepository); }

    @Test
    void addQuestion_whenSurveyNotFound_thenIllegalArgumentException() {
        QuestionEntryDto entryDto = new QuestionEntryDto();
        entryDto.setSurveyId(1L);

        Mockito.when(surveyRepository.findById(entryDto.getSurveyId()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> questionService.addQuestion(entryDto));
    }

    @Test
    void addQuestion_whenSurveyWithoutQuestions_thenSavedQuestionWith_questionOrder_Equal100() {
        QuestionEntryDto entryDto = new QuestionEntryDto("dummy text", 1L);

        Mockito.when(surveyRepository.findById(1L)).thenReturn(Optional.of(new Survey()));
        Mockito.when(questionRepository.findAllBySurveyId(entryDto.getSurveyId())).thenReturn(new ArrayList<>());
        questionService.addQuestion(entryDto);

        ArgumentCaptor<Question> argument = ArgumentCaptor.forClass(Question.class);

        Mockito.verify(questionRepository).save(argument.capture());
        Question questionToSave = argument.getValue();

        assertEquals(100, questionToSave.getQuestionOrder());
    }

    @Test
    void addQuestion_whenSurveyWithQuestions_thenSavedQuestionWith_questionOrder_incremented100() {
        QuestionEntryDto entryDto = new QuestionEntryDto("dummy text", 1L);

        Mockito.when(surveyRepository.findById(1L)).thenReturn(Optional.of(new Survey()));

        List<Question> foundedQuestionsBySurveyId = new ArrayList<>();
        foundedQuestionsBySurveyId.add(new Question(1L, "dummy text", 200, null));
        foundedQuestionsBySurveyId.add(new Question(2L, "dummy text", 300, null));
        foundedQuestionsBySurveyId.add(new Question(3L, "dummy text", 100, null));

        Mockito.when(questionRepository.findAllBySurveyId(entryDto.getSurveyId()))
                .thenReturn(foundedQuestionsBySurveyId);
        questionService.addQuestion(entryDto);

        ArgumentCaptor<Question> argument = ArgumentCaptor.forClass(Question.class);

        Mockito.verify(questionRepository).save(argument.capture());
        Question questionToSave = argument.getValue();

        assertEquals(400, questionToSave.getQuestionOrder());
    }

    @Test
    void updateQuestion_whenSurveyNotFound_thenIllegalArgumentException() {
        EditQuestionDto editQuestionDto = new EditQuestionDto();
        editQuestionDto.setId(1L);

        Mockito.when(surveyRepository.findById(editQuestionDto.getId()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> questionService.updateQuestion(editQuestionDto));
    }
}