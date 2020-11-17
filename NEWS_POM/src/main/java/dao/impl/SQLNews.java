package dao.impl;
import controller.command.impl.NewsCreateCommand;
import dao.pool.ConnectionPoolException;
import entity.News;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import dao.DAOException;
import dao.NewsDao;
import dao.pool.ConnectionPool;
import org.apache.log4j.Logger;


public class SQLNews implements NewsDao{
	private static final Logger logger = Logger.getLogger(SQLNews.class);

	private ConnectionPool pool = ConnectionPool.getInstance();
	private static final String NEWS_CREATE = "INSERT INTO news (title, brief, content, date) VALUES (?,?,?,?)";
	private static final String NEWS_SELECT_ALL = "SELECT * FROM news"; 
	private static final String NEWS_SELECT_BY_ID = "SELECT * FROM news WHERE id = ?";
	private static final String NEWS_UPDATE_BY_ID = "UPDATE news SET title = ? , brief = ? , content = ? , date = ?" + "WHERE id = ?";
	private static final String NEWS_DELETE_BY_ID ="DELETE FROM news WHERE id = ? ";
	private static final String NEWS_DELETE_SOME ="DELETE FROM news WHERE id = ? ";
	
//---------------------------DAO Select All news
		@Override
		public List<News> selectAll() throws DAOException {
			logger.info("logger in DAO select all");
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			
			String title = null;
			String brief = null;
			String content = null;
			LocalDate date = null;
			List<News> listNews = new ArrayList<>();
			
			try{
			
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(NEWS_SELECT_ALL);
			
			while (rs.next()) {
				title = rs.getString("title");
				brief = rs.getString("brief");
				content = rs.getString("content");
				date = LocalDate.parse((rs.getString("date")));
			
				News news = new News (title, brief, content, date);
				listNews.add(news);
			
				}
					
			} catch (SQLException e) {
				logger.error("Error in SQL select news" + " / " + e);
				throw new DAOException(e);
			} finally {
				pool.closeConnection(con,st);
			}
			return listNews;
		
		}

//---------------------DAO Create news
	@Override
	public void create (News news) throws DAOException {
		logger.info("logger in DAO create");

		Connection con = null;
		PreparedStatement ps = null;
		
		try{
		con = pool.takeConnection();
		ps=con.prepareStatement(NEWS_CREATE);
	 
		ps.setString(1, news.getTitle());
		ps.setString(2, news.getBrief());
		ps.setString(3, news.getContent());
		ps.setDate(4, java.sql.Date.valueOf(news.getDate()));
		
		ps.executeUpdate();
			logger.info("news was added to DB " + news);

		} catch (ConnectionPoolException e) {
			logger.error("Connection pool exception ", e);
			throw new DAOException("Error in dao take connection", e);
		} catch (SQLException e) {
			logger.error("Error in SQL create news" + " / " + e);
			throw new DAOException(e);
		} finally {
			pool.closeConnection(con,ps);
		}
	}

//----------------------------------DAO select by id
		@Override
		public News selectById(int id) throws DAOException {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			News news = new News();
			
			String title = null;
			String brief = null;
			String content = null;
			LocalDate date = null;

			try {
				con = pool.takeConnection();
				ps = con.prepareStatement(NEWS_SELECT_BY_ID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					title = rs.getString("title");
					brief = rs.getString("brief");
					content = rs.getString("content");
					date = LocalDate.parse((rs.getString("date")));
					
					news.setTitle(title);
					news.setBrief(brief);
					news.setContent(content);
					news.setDate(date);
				}

			} catch (ConnectionPoolException e) {
				logger.error("Connection pool exception selectById", e);
				throw new DAOException("Error in dao take connection", e);
			} catch (SQLException e) {
				logger.error("Error in SQL select by Id" + " / " + e);
				throw new DAOException(e);
			}finally {
				pool.closeConnection(con, ps, rs);
			}
			return news;
		}

		
//------------------------------Update by Id
	@Override
	public void updateById(News news, int id) throws DAOException {
		
			Connection con = null;
			PreparedStatement ps = null;

			try {

				con = pool.takeConnection();
				ps = con.prepareStatement(NEWS_UPDATE_BY_ID);

				
				ps.setString(1, news.getTitle());
				ps.setString(2, news.getBrief());
				ps.setString(3, news.getContent());
				ps.setDate(4, Date.valueOf(news.getDate()));
				ps.setInt(5, id);
				
				ps.executeUpdate();

			} catch (ConnectionPoolException e) {
				logger.error("Error take connection updateNews id ", e);
				throw new DAOException(e);
			} catch (SQLException e) {
				logger.error("Error in sql news updateNews id ", e);
				throw new DAOException(e);
			}finally {
				pool.closeConnection(con, ps);
			}
		}

//------------------------------Delete By Id
	
	@Override
	public void deleteById(int id) throws DAOException {
		
			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = pool.takeConnection();
				ps = con.prepareStatement(NEWS_DELETE_BY_ID);

				ps.setInt(1, id);
				
				ps.executeUpdate();

			} catch (ConnectionPoolException e) {
				logger.error("Error take connection deleteById ", e);
				throw new DAOException(e);
			} catch (SQLException e) {
				logger.error("Error in sql news delete id ", e);
				throw new DAOException(e);
			}finally {
				pool.closeConnection(con, ps);
			}		
	}
	
//-----------------------Delete Some news
	@Override
	public void deleteSome(int[] id) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		

		try {
			con = pool.takeConnection();


			for (int i = 0; i < id.length; i++) {
				ps.setInt(1, id[i]);
				ps = con.prepareStatement(NEWS_DELETE_SOME);
				ps.executeUpdate();
			}


		} catch (ConnectionPoolException e) {
			logger.error("Error take connection deleteSome ", e);
			throw new DAOException(e);
		} catch (SQLException e) {
			logger.error("Error in sql news deleteSome ", e);
			throw new DAOException(e);
		}finally {
			pool.closeConnection(con, ps);
		}		
	}

}

