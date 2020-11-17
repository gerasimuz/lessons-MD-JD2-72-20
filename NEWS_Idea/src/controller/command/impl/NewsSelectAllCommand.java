package controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;

import entity.News;
import service.ServiceException;
import service.ServiceProvider;

public class NewsSelectAllCommand implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<News> newsList = new ArrayList<>();
		
		try {
			 newsList = ServiceProvider.getInstance().getNewsService().selectAll();
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		req.setAttribute("news", newsList);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/news_select_all.jsp");
		
		requestDispatcher.forward(req, resp);
	}

}
