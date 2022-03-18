package controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.LibraryDTO;
import exception.BookNotFoundException;
import service.IntroductionService;

@Controller
public class IntroductionController {
	private IntroductionService introductionService;
	
	public void setIntroductionService(IntroductionService introductionService) {
		this.introductionService = introductionService;
	}
	@RequestMapping("/read/{num}")
	public String introduction(@PathVariable("num") Long num,Model model) {
		LibraryDTO book = (LibraryDTO) introductionService.showIntroduction(num);
		if(book == null) {
			throw new BookNotFoundException();
		}
		model.addAttribute("book", book);
		return "detail_form";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String bookNumberTypeMismatch() {
		return "exception/numTypeEx";
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public String noBook() {
		return "exception/noBook";
	}
	
	
}
