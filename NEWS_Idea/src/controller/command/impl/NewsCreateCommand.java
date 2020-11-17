package controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;
import entity.News;

public class NewsCreateCommand implements Command{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		News news = new News();
		
		news.setTitle(req.getParameter("title"));
		news.setBrief(req.getParameter("brief"));
		news.setContent(req.getParameter("content"));
		news.setDate(LocalDate.now());
		
	}
	
	
}
