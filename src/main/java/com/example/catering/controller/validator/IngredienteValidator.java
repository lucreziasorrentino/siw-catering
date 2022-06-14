package com.example.catering.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.catering.model.Ingrediente;
import com.example.catering.service.IngredienteService;

@Component
public class IngredienteValidator implements Validator{

	@Autowired
	private IngredienteService ingredienteService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if (this.ingredienteService.alreadyExists((Ingrediente)o)) {
			errors.reject("ingrediente.duplicato");	//messaggio di errore è stato messo in messages_IT.properties
		}
	}
	
	@Override
	public boolean supports(Class<?> pClass) {
		return Ingrediente.class.equals(pClass);
	}	
	

}
