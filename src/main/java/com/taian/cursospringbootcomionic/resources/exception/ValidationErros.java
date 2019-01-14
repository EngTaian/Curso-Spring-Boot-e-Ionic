package com.taian.cursospringbootcomionic.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErros extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationErros(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldMessage> errors) {
		this.errors = errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
