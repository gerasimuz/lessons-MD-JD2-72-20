package dao;

import dao.impl.SQLNews;

public class DaoProvider {
	
		private static final DaoProvider instance = new DaoProvider();

		private final NewsDao newsDao = new SQLNews();
		
		private DaoProvider() {}

		public static DaoProvider getInstance() {
			return instance;
		}
		
		public NewsDao getNewsDao() {
			return newsDao;
		}
}
