package service;

import java.util.List;

import entity.News;


public interface NewsService {
	List<News> selectAll () throws ServiceException;
	void create (News news) throws ServiceException;
	News selectById (int id) throws ServiceException;
	void updateById (News news, int id) throws ServiceException;
	void deleteById (int id) throws ServiceException;
	void deleteSomeNews(int[] id) throws ServiceException;
}
