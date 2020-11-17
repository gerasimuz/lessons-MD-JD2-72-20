package controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;
import entity.News;
import org.apache.log4j.Logger;
import service.NewsService;
import service.ServiceException;
import service.ServiceProvider;

public class NewsCreateCommand implements Command{

	private static final Logger logger = Logger.getLogger(NewsCreateCommand.class);

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		News news = new News();
		
		news.setTitle(req.getParameter("title"));
		news.setBrief(req.getParameter("brief"));
		news.setContent(req.getParameter("content"));
		news.setDate(LocalDate.now());


		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		System.out.println(serviceProvider);
		NewsService newsService = serviceProvider.getNewsService();

		try{
			newsService.create(news);
		} catch (ServiceException e) {
			logger.error("Error in NewsService.create command" + " / " + e);
			e.printStackTrace();

		}
		resp.sendRedirect("controller?command=news_select_all");

	}
}

