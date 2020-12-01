package by.itacademy.gera.dao;

import java.util.List;

import by.itacademy.gera.entity.News;

public interface NewsDAO {
    void createNews(News news) throws DAOException;

    List<News> selectAllNews() throws DAOException;

    News selectNews(int id) throws DAOException;

    void deleteNews(int id) throws DAOException;

    void deleteSelectedNews(int[] id) throws DAOException;
}