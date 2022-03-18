package service;

import dao.LibraryDAO;

public class IntroductionService {
	private LibraryDAO libraryDAO;
	
	public void setLibraryDAO(LibraryDAO libraryDAO) {
		this.libraryDAO = libraryDAO;
	}
	
	public Object showIntroduction(Long num) {
		Object book = libraryDAO.selectIntroduction(num);
		return book;
	}
}