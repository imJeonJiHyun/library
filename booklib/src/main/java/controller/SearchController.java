package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.LibraryDTO;
import command.SearchCommand;
import service.SearchService;
import validate.SearchCommandValidator;

@Controller
public class SearchController {
	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search(@ModelAttribute("command") SearchCommand searchCommand,Errors errors, Model model) {
		new SearchCommandValidator().validate(searchCommand, errors);
		List<LibraryDTO> books = searchService.search(searchCommand);
		model.addAttribute("books", books);
		
		return "book_list";
	}
}
