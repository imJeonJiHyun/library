package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import command.RegistCommand;
import exception.*;
import service.RegistService;
import validate.RegistCommandValidator;


@Controller
public class RegistController {
	private RegistService registService;
	public void setRegistService(RegistService registService) {
		this.registService = registService;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String goRegistForm(@ModelAttribute("regForm") RegistCommand registCommand) {
		return "reg_form";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String regist(@ModelAttribute("regForm") RegistCommand registCommand, Errors errors) {
		
		new RegistCommandValidator().validate(registCommand, errors);
		
		if(errors.hasErrors()) {
			return "reg_form";
		}
		try {
			registService.register(registCommand);
			return "redirect:/list";
		}catch(AlreadyExistingBookException e) {
			errors.rejectValue("isbn", "dupBook");
			return "reg_form";
		}catch(NoImageException e) {
			errors.rejectValue("img", "noImage");
			return "reg_form";
		}
	}
}