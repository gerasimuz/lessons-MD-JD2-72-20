package service;

import service.impl.NewsServiceImpl;

public class ServiceProvider {

	private static ServiceProvider instance;

	static{
		try {
			instance = new ServiceProvider();
		} catch (Throwable e) {
			System.out.println("ERROR");
		}
	} 

	private NewsService newsService = new NewsServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public NewsService getNewsService() {
			return newsService;
	}
}
