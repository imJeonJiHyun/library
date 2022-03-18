package dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import dto.LibraryDTO;

public class LibraryDAOImpl implements LibraryDAO {
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	public LibraryDAOImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	@Override
	public List<LibraryDTO> selectAll() {
		return sqlSessionTemplate.selectList("selectAll");
	}

	@Override
	public List<LibraryDTO> selectByName(String name) {
		return sqlSessionTemplate.selectList("selectByName", name);
	}

	@Override
	public List<LibraryDTO> selectByWriter(String writer) {
		return sqlSessionTemplate.selectList("selectByWriter", writer);
	}

	@Override
	public List<LibraryDTO> selectByPublisher(String publisher) {
		return sqlSessionTemplate.selectList("selectByPublisher", publisher);
	}

	@Override
	public Object selectIntroduction(Long num) {
		return sqlSessionTemplate.selectOne("selectIntroduction", num);
	}

	@Override
	public Object selectByIsbn(Long isbn) {
		return sqlSessionTemplate.selectOne("selectByIsbn", isbn);
	}

	@Override
	public int count() {
		return sqlSessionTemplate.selectOne("count");
	}

	@Override
	public void update(LibraryDTO libraryDTO) {
		sqlSessionTemplate.update("update", libraryDTO);
	}

	@Override
	public void insert(LibraryDTO libraryDTO) {
		sqlSessionTemplate.update("insert", libraryDTO);
	}

	@Override
	public void delete(Long num) {
		sqlSessionTemplate.update("delete", num);
	}
}