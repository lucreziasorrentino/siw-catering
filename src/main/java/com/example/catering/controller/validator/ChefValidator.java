package com.example.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.catering.model.Chef;
import com.example.catering.service.ChefService;

@Component
public class ChefValidator implements Validator{

	@Autowired
	private ChefService chefService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if (this.chefService.alreadyExists((Chef)o)) {
			errors.reject("chef.duplicato");	//messaggio di errore è stato messo in messages_IT.properties
		}
	}
	
	@Override
	public boolean supports(Class<?> pClass) {
		return Chef.class.equals(pClass);
	}	
	

}
