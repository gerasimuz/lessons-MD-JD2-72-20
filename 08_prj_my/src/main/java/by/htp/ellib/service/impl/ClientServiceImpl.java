package by.htp.ellib.service.impl;

import by.htp.ellib.dao.DAOProvider;
import by.htp.ellib.dao.DaoException;
import by.htp.ellib.dao.UserDAO;
import by.htp.ellib.entity.User;
import by.htp.ellib.service.ClientService;
import by.htp.ellib.service.ServiceException;
import by.htp.ellib.entity.UserData;
import by.htp.ellib.service.validation.GredentionalValidator;

public class ClientServiceImpl implements ClientService{

	@Override
	public User authorization(String login, String password) throws ServiceException{

		if(!GredentionalValidator.isCorrect(login, password)) {
			throw new ServiceException("message");

		}
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();

		User user = null;

		try {
			user = userDAO.authentification(login, password);
		}catch (DaoException e){
			throw new ServiceException(e);
		}

		return user;
	}


	@Override
	public boolean registration(UserData user) throws ServiceException{
		
		//validation
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();

		try {
			return userDAO.registration(user);
		}catch (DaoException e){
			throw new ServiceException(e);
			
		}

	}
}


