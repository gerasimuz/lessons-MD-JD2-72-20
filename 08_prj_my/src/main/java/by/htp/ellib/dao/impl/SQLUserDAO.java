package by.htp.ellib.dao.impl;

import java.sql.SQLException;

import by.htp.ellib.dao.DaoException;
import by.htp.ellib.dao.UserDAO;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;

public class SQLUserDAO implements UserDAO{

	@Override
	public User authentification(String login, String password)  throws DaoException{
		try {
			throw new SQLException();
		}catch (SQLException e) {
			throw new DaoException(e);
		}
		
		//return null;
	}

	@Override
	public boolean registration(UserData userData) throws DaoException {
		try {
			throw new SQLException();
		}catch (SQLException e) {
			throw new DaoException(e);
		}
		
		//return null;
	}
}

