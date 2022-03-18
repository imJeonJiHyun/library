package dao;

import java.util.List;

import dto.LibraryDTO;

public interface LibraryDAO {
	public List<LibraryDTO> selectAll();
	public List<LibraryDTO> selectByName(String name);
	public List<LibraryDTO> selectByWriter(String writer);
	public List<LibraryDTO> selectByPublisher(String publisher);
	public Object selectIntroduction(Long num);
	public Object selectByIsbn(Long isbn);
	//여러가지 DTO를 사용할 가능성이 있어서 Object 사용 (다형성)
	public int count();
	public void update(LibraryDTO libraryDTO);
	public void insert(LibraryDTO libraryDTO);
	public void delete(Long num);
}