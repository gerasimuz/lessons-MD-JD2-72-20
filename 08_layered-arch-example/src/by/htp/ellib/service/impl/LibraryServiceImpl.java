package by.htp.ellib.service.impl;

import java.util.List;

import by.htp.ellib.dao.BookDAO;
import by.htp.ellib.dao.DAOProvider;
import by.htp.ellib.dao.DaoException;
import by.htp.ellib.entity.Book;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceException;

public class LibraryServiceImpl implements LibraryService{

	@Override
	public List<Book> find(String criteria) throws ServiceException{
		// validation
		
		
		DAOProvider provider = DAOProvider.getInstance();
		BookDAO bookDAO = provider.getBookDAO();
		
		List <Book> books;
		
		try {
			books = bookDAO.find(criteria);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
		return books;
	}

}
