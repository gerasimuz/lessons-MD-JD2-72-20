package controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;
import org.apache.log4j.Logger;
import entity.News;
import service.NewsService;
import service.ServiceException;
import service.ServiceProvider;

public class NewsSelectAllCommand implements Command {


	/**
	 * controller method to show all news on Main page
	 */
	private static final Logger logger = Logger.getLogger(NewsSelectAllCommand.class);


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<News> newsList = new ArrayList<>();

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		NewsService newsService = serviceProvider.getNewsService();

		try {
			newsList = newsService.selectAll();


		} catch (ServiceException e) {

			logger.error("Error NewsSelectAllCommand " + " / " + e);
		}
		req.setAttribute("news", newsList);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/news_select_all.jsp");
		
		requestDispatcher.forward(req, resp);
	}

}
