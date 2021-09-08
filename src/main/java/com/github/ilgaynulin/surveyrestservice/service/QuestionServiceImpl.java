package com.github.ilgaynulin.surveyrestservice.service;

import com.github.ilgaynulin.surveyrestservice.dto.EditQuestionDto;
import com.github.ilgaynulin.surveyrestservice.dto.QuestionEntryDto;
import com.github.ilgaynulin.surveyrestservice.model.Question;
import com.github.ilgaynulin.surveyrestservice.model.Survey;
import com.github.ilgaynulin.surveyrestservice.repository.QuestionRepository;
import com.github.ilgaynulin.surveyrestservice.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{
    QuestionRepository questionRepository;
    SurveyRepository surveyRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, SurveyRepository surveyRepository) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    @Override
    public Page<Question> getQuestionsBySurveyId(int pageNum, int pageSize, String sortBy, Optional<Long> surveyId) {
        if(surveyId.isPresent()) {
            return questionRepository.findAllBySurveyId(surveyId.get()
                    , PageRequest.of(pageNum, pageSize, Sort.by(sortBy).ascending())
            );
        }
        return questionRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(sortBy).ascending()));
    }

    @Override
    public Question addQuestion(QuestionEntryDto entryDto) {
        Survey survey = surveyRepository.findById(entryDto.getSurveyId()).orElse(null);
        if(survey == null) {
            return null;
        }
        int newQuestionOrderNum = questionRepository.findAllBySurveyId(entryDto.getSurveyId())
                .stream()
                .map(q -> q.getQuestionOrder())
                .max(Comparator.naturalOrder()).orElse(0);
        newQuestionOrderNum += 100;

        Question newQuestion = new Question();
        newQuestion.setQuestionText(entryDto.getQuestionText());
        newQuestion.setQuestionOrder(newQuestionOrderNum);
        newQuestion.setSurvey(survey);

        return questionRepository.save(newQuestion);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public Question saveQuestion(EditQuestionDto editDto) {

        Survey survey = surveyRepository.findById(editDto.getSurveyId()).orElse(null);
        if(survey == null) {
            return null;
        }
        Question question = editDto.toQuestion();
        question.setSurvey(survey);
        return questionRepository.save(question);
    }
}
