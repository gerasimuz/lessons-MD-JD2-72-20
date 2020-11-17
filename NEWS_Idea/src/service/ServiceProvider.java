package service;

import service.impl.NewsServiceImpl;

public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();

	private ServiceProvider() {
	}
	
	private final NewsService newsService = new NewsServiceImpl();
		
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public NewsService getNewsService() {
			return newsService;
	}
}
