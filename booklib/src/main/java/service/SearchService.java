package service;

import java.util.List;

import dao.LibraryDAO;
import dto.LibraryDTO;
import command.SearchCommand;

public class SearchService {
private LibraryDAO libraryDAO;
	
	public void setLibraryDAO(LibraryDAO libraryDAO) {
		this.libraryDAO = libraryDAO;
	}
	
	public List<LibraryDTO> search(SearchCommand searchCommand) {
		List<LibraryDTO> books = null;
		if(searchCommand.getSearchType().equals("bookname")) {
			books = libraryDAO.selectByName(searchCommand.getSearchValue());
		}else if(searchCommand.getSearchType().equals("writer")) {
			books = libraryDAO.selectByWriter(searchCommand.getSearchValue());
		}else if(searchCommand.getSearchType().equals("publisher")){
			books = libraryDAO.selectByPublisher(searchCommand.getSearchValue());
		}
		return books;
	}
}
