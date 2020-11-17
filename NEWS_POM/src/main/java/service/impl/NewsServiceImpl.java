package service.impl;
import java.util.List;

import controller.command.impl.NewsSelectAllCommand;
import dao.DAOException;
import dao.DaoProvider;
import dao.NewsDao;
import entity.News;
import org.apache.log4j.Logger;
import service.NewsService;
import service.ServiceException;
import service.validation.NewsValidation;

public class NewsServiceImpl implements NewsService{

	private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);
	private DaoProvider provider = DaoProvider.getInstance();
	
	private NewsDao newsDao = provider.getNewsDao();

//-----------------read all news+++
	@Override
	public List<News> selectAll() throws ServiceException {
		try {List<News> newsList = newsDao.selectAll();
		return newsList;
		} catch (DAOException e) {
			throw new ServiceException("Error service selectAll" + e);
		} 
	}
	
//-------------------create News+++
	@Override
	public void create(News news) throws ServiceException {
		
		if (!NewsValidation.isCorrectLength(news)){
			logger.error("Error create validation in Dao");
			throw new ServiceException("Validation is not correct");
			}
		
			try {newsDao.create(news);
			} catch (DAOException e) {
				logger.error("Error service Create News " + e);
				throw new ServiceException("Error service Create News");
		}
}
	
//----------------select News by Id++++
	@Override
	public News selectById(int id) throws ServiceException {
		
		if (!NewsValidation.digitIsPositive(id)) {
			throw new ServiceException("Validation os not correct");
		} else {

			try {
				News news = newsDao.selectById(id);
				return news;
			} catch (DAOException e) {
				logger.error("Error Error service selectById in Dao" + " / " + e);
				throw new ServiceException("Error service selectById" + e);

			}
		}
	}

//--------------------Update News by Id
	@Override
	public void updateById(News news, int id) throws ServiceException {
		if(!NewsValidation.isCorrectLength(news)) {
			throw new ServiceException("Validation UpdateById is Not correct");
		}
		
		if (!NewsValidation.digitIsPositive(id)) {
			throw new ServiceException("is not positive digit in Id UpdateById");
		}
		
		try {newsDao.updateById(news, id);
		} catch (DAOException e) {
			throw new ServiceException("error service UpdateByID");
		}
	}

//--------------------Delete News By Id
	@Override
	public void deleteById(int id) throws ServiceException {
		if (!NewsValidation.digitIsPositive(id)) {
			throw new ServiceException("is not positive digit in Id DeleteById");
		}
		try {
			newsDao.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException("error service deleteByID");
		}
	}

//------------------Delete Some news
	@Override
	public void deleteSomeNews(int[] id) throws ServiceException {
		for(int i =1; i<=id.length;i++) {
			int num = id[i];
			try {
				newsDao.deleteById(num);
			} catch (DAOException e) {
				throw new ServiceException("error service deleteByID for Some news");
			}
		}
	}

	
}
