package by.itacademy.gera.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.gera.dao.DAOException;
import by.itacademy.gera.dao.NewsDAO;
import by.itacademy.gera.entity.News;

@Repository
public class SQLNewsDAOImpl implements NewsDAO {
	private static final Logger logger = Logger.getLogger(SQLNewsDAOImpl.class);

	@Autowired
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}

	private SessionFactory mySessionFactory;

	private static final String HQL_SELECT_ALL = "from News";
	private static final String HQL_SELECT_BY_ID = "from News where id =:newsId";

	/**
	 * Creates new or updates existing News
	 *
	 * @param news
	 * @throws DAOException
	 */
	@Override
	public void createNews(News news) throws DAOException {

		Session currentSession = mySessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(news);
	}

	/**
	 * Finds all News from DB.
	 * <p>
	 *
	 * @throws DAOException
	 */
	@Override
	public List<News> selectAllNews() throws DAOException {

		Session currentSession = mySessionFactory.getCurrentSession();

		Query<News> query = currentSession.createQuery(HQL_SELECT_ALL, News.class);

		List<News> news = query.getResultList();

		return news;
	}

	/**
	 * Finds News by id.
	 * <p>
	 *
	 * @throws DAOException
	 */
	@Override
	public News selectNews(int id) throws DAOException {

		Session currentSession = mySessionFactory.getCurrentSession();

		Query<News> theQuery = currentSession.createQuery(HQL_SELECT_BY_ID, News.class);
		theQuery.setParameter("newsId", id);
		News news = theQuery.uniqueResult();

		return news;
	}

	/**
	 * Deletes News from DB.
	 * <p>
	 * Should execute in Session (transaction) earlier opened in Service layer.
	 *
	 * @throws DAOException
	 */
	@Override
	public void deleteNews(int id) throws DAOException {

		try {
				Session currentSession = mySessionFactory.getCurrentSession();

			News news = currentSession.load(News.class, id);

			if (null != news) {
				currentSession.delete(news);
			}
		} catch (HibernateException e) {
			logger.error("Error deleting News /" + e);
			throw new DAOException("Error deleting News /" + e);
		}
	}

	/**
	 * Deletes array of News from DB
	 *
	 * @param id - id's checkboxes selected
	 * @throws DAOException
	 */
	@Override
	public void deleteSelectedNews(int[] id) throws DAOException {

		for (int i = 0; i < id.length; i++) {
			deleteNews(id[i]);
		}
	}
}