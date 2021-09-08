package com.github.ilgaynulin.surveyrestservice.repository;

import com.github.ilgaynulin.surveyrestservice.model.Survey;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class SurveySpecification implements Specification<Survey> {
    private SearchCriteria criteria;

    public SurveySpecification(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }
    @Override
    public Predicate toPredicate(Root<Survey> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if(criteria.getOperation().equalsIgnoreCase("during")) {
            if(root.get(criteria.getKey()).getJavaType() == Date.class) {
                Date date = (Date) criteria.getValue();
                LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                LocalDateTime startDayLocal = localDateTime.toLocalDate().atStartOfDay();
                LocalDateTime endDayLocal = startDayLocal.plusDays(1).minusSeconds(1);

                return criteriaBuilder.between(
                        root.get(criteria.getKey()),
                        java.sql.Timestamp.valueOf(startDayLocal),
                        java.sql.Timestamp.valueOf(endDayLocal)
                );
            } else {
                throw new IllegalStateException("In SearchCriteria: Used operation \"during\" not with java.util.Date object");
            }
        } else if (criteria.getOperation().equalsIgnoreCase("equals")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
