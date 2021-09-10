package com.github.ilgaynulin.surveyrestservice.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.ilgaynulin.surveyrestservice.model.Survey;
import com.github.ilgaynulin.surveyrestservice.repository.SearchCriteria;
import com.github.ilgaynulin.surveyrestservice.service.SurveyService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc
class SurveyRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SurveyService serviceMock;

    @Test
    public void contextLoaded() {

    }
    @Test
    public void getAllSurveys_NotEmptyRepo_HttpStatusOk() throws Exception {
        Queue<SearchCriteria> emptySearchCriteria = new LinkedList<>();
        List<Survey> surveys = new ArrayList<Survey>();
        surveys.add(new Survey());

        when(serviceMock.getAll(0, 10, "name", emptySearchCriteria)).thenReturn(new PageImpl<Survey>(surveys));

        mvc.perform(get("/surveys?sortBy=name"))
                .andDo(print()).andExpect(status().isOk());
    }
}