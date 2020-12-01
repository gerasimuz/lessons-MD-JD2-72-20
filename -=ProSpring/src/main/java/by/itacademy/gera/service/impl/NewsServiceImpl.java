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
					"Validation isEmptyData FAILED / Some of the fields: 'section', 'author', 'brief', 'content' are empty");
			throw new ServiceException("Validation isEmptyData FAILED / Some of the fields: 'section', 'author', 'brief', 'content' are empty");
		}

		else if (NewsValidation.isCorrectLength(news)) {
			logger.error(
					"Validation isFormLengthNotCorrect  FAILED / Some of the fields: 'section', 'author', 'brief', 'content' are over length");
			throw new ServiceException("Validation isFormLengthNotCorrect  FAILED / Some of the fields: 'section', 'author', 'brief', 'content' are over length");
		}

		try {
			newsDAO.createNews(news);
		} catch (DAOException e) {
			logger.error("Error creating news in DAO/ ", e);
			throw new ServiceException(e);
		}
	}

	/**
	 * Finds all News from DB
	 *
	 * @return List<News>
	 * @throws ServiceException
	 */
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

	/**
	 * Finds News by id from DB
	 * news == null, if News id is not unique in DB, it must be unique.
	 *
	 * @param id
	 * @return news
	 * @throws ServiceException
	 */
	@Override
	@Transactional
	public News selectNews(int id) throws ServiceException {

		News news;

		try {
			news = newsDAO.selectNews(id);
			if (news == null) {
				throw new ServiceException("Error select news by id, because id is not unique or DB problem");
			}
		} catch (DAOException e) {
			logger.error("Error selecting news by id, because id is not unique or DB problem / ", e);
			throw new ServiceException("Error selecting news by id, because id is not unique or DB problem / ", e);
		}
		return news;
	}

	/**
	 * Deletes News by id from DB
	 *
	 * @param id
	 * @throws ServiceException
	 */
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

	/**
	 * Deletes list of News from DB
	 *
	 * @param id - array of checkboxes selected from Form
	 * @throws ServiceException
	 */
	@Override
	@Transactional
	public void deleteSelectedNews(int[] id) throws ServiceException {

		try {
			newsDAO.deleteSelectedNews(id);
		} catch (DAOException e) {
			logger.error("Error group News deleting in DAO / ", e);
			throw new ServiceException("Error group News deleting in DAO / ", e);
		}
	}
}