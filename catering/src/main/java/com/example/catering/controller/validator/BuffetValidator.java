package com.example.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.catering.model.Buffet;
import com.example.catering.service.BuffetService;

@Component
public class BuffetValidator implements Validator{

	@Autowired
	private BuffetService buffetService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if (this.buffetService.alreadyExists((Buffet)o)) {
			errors.reject("buffet.duplicato");	//messaggio di errore è stato messo in messages_IT.properties
		}
	}
	
	@Override
	public boolean supports(Class<?> pClass) {
		return Buffet.class.equals(pClass);
	}	
	

}
