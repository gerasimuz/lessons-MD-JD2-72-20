package by.htp.ellib.dao;

import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;

public interface UserDAO {

	User authentification (String login, String password) throws DaoException;
	
	boolean registration (UserData userData) throws DaoException;
	
	
}
