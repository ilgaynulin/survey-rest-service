package com.github.ilgaynulin.surveyrestservice.service;

import com.github.ilgaynulin.surveyrestservice.dto.SurveyUpdateDto;
import com.github.ilgaynulin.surveyrestservice.repository.SurveyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;


class SurveyServiceImplTest {
    SurveyRepository surveyRepository = Mockito.mock(SurveyRepository.class);
    SurveyService surveyService;

    @BeforeEach
    void initService() {
        surveyService = new SurveyServiceImpl(surveyRepository);
    }

    @Test
    void update_whenSurveyNotFound_thenIllegalArgumentException() {
        Mockito.when(surveyRepository.findById(1L))
                .thenReturn(Optional.empty());
        SurveyUpdateDto surveyUpdateDto = new SurveyUpdateDto();
        surveyUpdateDto.setId(1L);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> surveyService.update(surveyUpdateDto));
    }
}