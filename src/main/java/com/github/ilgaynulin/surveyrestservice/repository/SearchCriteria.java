package com.github.ilgaynulin.surveyrestservice.repository;

/**
 * The class is used as a container for storing parameters for the criteria builder class
 * (e.g. {@link SurveySpecification}), by which we filter the objects that we want to get
 * from the repository
 * @author ilgaynulin
 */
public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;

    public SearchCriteria() {
    }

    public SearchCriteria(final String key, final String operation, final Object value) {
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(final String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

}