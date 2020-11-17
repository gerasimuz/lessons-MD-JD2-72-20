package service.impl;
import java.util.List;
import dao.DAOException;
import dao.DaoProvider;
import dao.NewsDao;
import entity.News;
import service.NewsService;
import service.ServiceException;
import service.validation.NewsValidation;

public class NewsServiceImpl implements NewsService{

	private DaoProvider provider = DaoProvider.getInstance();
	
	private NewsDao newsDao = provider.getNewsDao();

//-----------------read all news+++
	@Override
	public List<News> selectAll() throws ServiceException {
		try {List<News> newsList = newsDao.selectAll();
		return newsList;
		} catch (DAOException e) {
			throw new ServiceException("Error service selectAll");
		} 
	}
	
//-------------------create News+++
	@Override
	public void create(News news) throws ServiceException {
		
		if (!NewsValidation.isCorrectLength(news)){
			throw new ServiceException("Validation is not correct");
			}
		
			try {newsDao.create(news);
			} catch (DAOException e) {
				throw new ServiceException("Error service Create News");
		}
}
	
//----------------select News by Id++++
	@Override
	public News selectById(int id) throws ServiceException {
		
		if (!NewsValidation.digitIsPositive(id)) {
			throw new ServiceException("Not positive digit in Id Validation");	
		}
		
			try {News news = newsDao.selectById(id);
			return news;
			} catch (DAOException e) {
				throw new ServiceException("Error service selectById");
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
