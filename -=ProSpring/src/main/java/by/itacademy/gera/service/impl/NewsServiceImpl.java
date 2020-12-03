package by.itacademy.gera.service.impl;

import java.util.List;

import by.itacademy.gera.service.validation.NewsValidation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.itacademy.gera.dao.DAOException;
import by.itacademy.gera.dao.NewsDAO;
import by.itacademy.gera.entity.News;
import by.itacademy.gera.service.NewsService;
import by.itacademy.gera.service.ServiceException;

@Service
public class NewsServiceImpl implements NewsService {
	private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);

	@Autowired
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	private NewsDAO newsDAO;

	/**	 Creating/Updating news */

	@Override
	@Transactional
	public void createNews(News news) throws ServiceException {

		if (NewsValidation.isNoteEmptyField(news)) {
			logger.error(
					"Validation failed. Empty fields ");
			throw new ServiceException("Validation failed. Empty fields");
		}

		else if (NewsValidation.isCorrectLength(news)) {
			logger.error(
					"Length Validation is incorrect");
			throw new ServiceException("Length Validation is incorrect");
		}

		try {
			newsDAO.createNews(news);
		} catch (DAOException e) {
			logger.error("Error creating news in DAO/ ", e);
			throw new ServiceException(e);
		}
	}

	/** Finds all News from DB */

	@Override
	@Transactional
	public List<News> selectAllNews() throws ServiceException {

		List<News> news;

		try {
			news = newsDAO.selectAllNews();
		} catch (DAOException e) {
			logger.error("Error selecting  all news List in DAO / " + e);
			throw new ServiceException("Error selecting  all news List in DAO / " + e);
		}
		return news;
	}

	/** News by id */

	@Override
	@Transactional
	public News selectNews(int id) throws ServiceException {

		News news;

		try {
			news = newsDAO.selectNews(id);
			if (news == null) {
				throw new ServiceException("Error select news by id");
			}
		} catch (DAOException e) {
			logger.error("Error selecting news by id ", e);
			throw new ServiceException("Error selecting news by id", e);
		}
		return news;
	}

	/** Deletes News by id */

	@Override
	@Transactional
	public void deleteNews(int id) throws ServiceException {

		try {
			newsDAO.deleteNews(id);
		} catch (DAOException e) {
			logger.error("Error deleting News  in DAO / ", e);
			throw new ServiceException("Error deleting News  in DAO / ", e);
		}
	}

	/** Deletes list of News from DB */

	@Override
	@Transactional
	public void deleteSelectedNews(int[] id) throws ServiceException {

		try {
			newsDAO.deleteSelectedNews(id);
		} catch (DAOException e) {
			logger.error("Error group deleting ", e);
			throw new ServiceException("Error group deleting ", e);
		}
	}
}