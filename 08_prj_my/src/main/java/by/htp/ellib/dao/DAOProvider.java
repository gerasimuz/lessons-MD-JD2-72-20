package by.htp.ellib.dao;

import by.htp.ellib.dao.impl.SQLBookDAO;
import by.htp.ellib.dao.impl.SQLUserDAO;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final BookDAO bookDAO = new SQLBookDAO();

	
	private DAOProvider () {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public BookDAO getBookDAO() {
		return bookDAO;
		
	}
	
	public static DAOProvider getInstance() {
		return instance;
	}

}
