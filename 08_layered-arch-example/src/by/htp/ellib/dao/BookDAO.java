package by.htp.ellib.dao;

import java.util.List;

import by.htp.ellib.entity.Book;

public interface BookDAO {
	
	List<Book> find (String criteria) throws DaoException;

}
