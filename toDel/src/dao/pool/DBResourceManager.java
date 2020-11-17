package dao.pool;


import java.util.ResourceBundle;

public class DBResourceManager {
	
	private final static DBResourceManager instance = new DBResourceManager();
	private ResourceBundle bundle =	ResourceBundle.getBundle("dao.pool.db.properties");

	public static DBResourceManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}

}