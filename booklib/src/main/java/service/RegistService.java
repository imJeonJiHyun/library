package service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import dto.LibraryDTO;
import command.RegistCommand;
import dao.LibraryDAO;
import exception.*;

public class RegistService {
	private LibraryDAO libraryDAO;

	public void setLibraryDAO(LibraryDAO libraryDAO) {
		this.libraryDAO = libraryDAO;
	}
	
	public void register(RegistCommand rc) {
		String path = "C:\\aaa\\upload";
		LibraryDTO hasbook = (LibraryDTO) libraryDAO.selectByIsbn(rc.getIsbn());
		
		//커맨드객체에 담긴 isbn이 db에 이미 있다면 예외처리(중복)
		if(hasbook != null) {
			throw new AlreadyExistingBookException();
		}
		
		MultipartFile img = rc.getImg(); //커맨드 객체에서 멀티파트파일 타입 객체 불러옴
		String oriFilename = img.getOriginalFilename();//업로드 당시 파일 이름
		//String fileExtension = 	oriFilename.substring(oriFilename.lastIndexOf("."));
		//System.out.println("확장자 : " + fileExtension);
		
		String savedFilename = UUID.randomUUID().toString().replaceAll("-", "")+oriFilename;
		//서버에 저장될 파일 이름은 중복을 피하기 위해 uuid클래스로 랜덤 생성해 처리
		InputStream inputStream = null;
		File file = null;
		try{//자동 클로즈는 resource try catch 문
			//저장된 파일이 아닌 멀티파트파일 객체에 직접 스트림 꽂음
			inputStream = img.getInputStream();
			System.out.println("Content Type : " + img.getContentType());
			if(savedFilename != null) {
				if(savedFilename.endsWith("jpg")||savedFilename.endsWith("png")) {
					file = new File(path, savedFilename); //파일 객체 생성
					inputStream.close(); //!!!!!트렌스퍼 전에 닫아주기
					img.transferTo(file);	//파일 저장
				}else {
					System.out.println("이미지가 아닙니다.");
					throw new NoImageException();
				}
			}
			
			LibraryDTO newBook = new LibraryDTO();
			newBook.setIsbn(rc.getIsbn());
			newBook.setName(rc.getName());
			newBook.setWriter(rc.getWriter());
			newBook.setPublisher(rc.getPublisher());
			newBook.setGenre(rc.getGenre());
			newBook.setPrice(rc.getPrice());
			newBook.setIntroduction(rc.getIntroduction());
			newBook.setOrifilename(oriFilename);
			newBook.setSavedfilename(savedFilename);
			//이미지로가 아닌 이미지의 이름으로 저장해야함 / command에서 가져 온 사진의 이름을 추출해서 dto에 넣기
		
			libraryDAO.insert(newBook);
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	}
}