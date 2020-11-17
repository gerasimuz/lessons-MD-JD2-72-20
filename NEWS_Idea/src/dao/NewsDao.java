 package dao;

import java.util.List;

import entity.News;
import service.ServiceException;

public interface NewsDao {
	List<News> selectAll() throws DAOException;
	void create(News news) throws DAOException;
	News selectById(int id)throws DAOException;
	void updateById(News news, int id) throws DAOException;
	void deleteById(int id) throws DAOException; 
	void deleteSome(String[] id) throws DAOException;
}
