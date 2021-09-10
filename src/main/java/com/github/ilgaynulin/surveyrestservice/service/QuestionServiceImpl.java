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
    public Question addQuestion(QuestionEntryDto entryDto){
        Optional<Survey> foundSurvey = surveyRepository.findById(entryDto.getSurveyId());
        if(!foundSurvey.isPresent()) {
            throw new IllegalArgumentException("Survey with id = " + entryDto.getSurveyId() + " not found");
        }
        int newQuestionOrderNum = questionRepository.findAllBySurveyId(entryDto.getSurveyId())
                .stream()
                .map(q -> q.getQuestionOrder())
                .max(Comparator.naturalOrder()).orElse(0);
        newQuestionOrderNum += 100;

        Question newQuestion = new Question();
        newQuestion.setQuestionText(entryDto.getQuestionText());
        newQuestion.setQuestionOrder(newQuestionOrderNum);
        newQuestion.setSurvey(foundSurvey.get());

        return questionRepository.save(newQuestion);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public Question updateQuestion(EditQuestionDto editDto) {
        Optional<Question> foundQuestion = questionRepository.findById(editDto.getId());
        if(!foundQuestion.isPresent()) {
            throw new IllegalArgumentException("Question with id = " + editDto.getId() + " not found");
        }

        Question updatedQuestion = foundQuestion.get();
        updatedQuestion.setQuestionText(editDto.getQuestionText());
        updatedQuestion.setQuestionOrder(editDto.getQuestionOrder());

        return questionRepository.save(updatedQuestion);
    }
}
