package by.htp.ellib.service;

import java.util.List;

import by.htp.ellib.entity.Book;

public interface LibraryService {
	
	List<Book> find (String criteria)  throws ServiceException;
	
}
