package com.example.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.catering.model.Piatto;
import com.example.catering.service.PiattoService;

@Component
public class PiattoValidator implements Validator{

	@Autowired
	private PiattoService piattoService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if (this.piattoService.alreadyExists((Piatto)o)) {
			errors.reject("piatto.duplicato", "Questo piatto esiste già!");	//messaggio di errore è stato messo in messages_IT.properties
		}
	}
	
	@Override
	public boolean supports(Class<?> pClass) {
		return Piatto.class.equals(pClass);
	}	
	

}
