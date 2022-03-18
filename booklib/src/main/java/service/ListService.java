package service;

import java.util.List;

import dao.LibraryDAO;
import dto.LibraryDTO;

public class ListService {
	private LibraryDAO libraryDAO;
	
	public void setLibraryDAO(LibraryDAO libraryDAO) {
		this.libraryDAO = libraryDAO;
	}
	
	public List<LibraryDTO> showList() {
		return libraryDAO.selectAll();
	}	
}