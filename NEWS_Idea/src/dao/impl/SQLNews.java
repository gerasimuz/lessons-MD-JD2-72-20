package dao.impl;
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


public class SQLNews implements NewsDao{
	
	private final ConnectionPool pool = ConnectionPool.getInstance();
	private static final String NEWS_CREATE = "INSERT INTO news (title, brief, content, date) VALUES (?,?,?,?)";
	private static final String NEWS_SELECT_ALL = "SELECT * FROM news"; 
	private static final String NEWS_SELECT_BY_ID = "SELECT * FROM news WHERE id = ?";
	private static final String NEWS_UPDATE_BY_ID = "UPDATE news SET title = ? , brief = ? , content = ? , date = ?" + "WHERE id = ?";
	private static final String NEWS_DELETE_BY_ID ="DELETE FROM news WHERE id = ? ";
	private static final String NEWS_DELETE_SOME ="DELETE FROM news WHERE id = ? ";
	
//---------------------------DAO Select All news	
		@Override
		public List<News> selectAll() throws DAOException {
			 
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
				throw new DAOException(e);
			} finally {
				pool.closeConnection(con,st);
			}
			return listNews;
		
		}

//---------------------DAO Create news
	@Override
	public void create (News news) throws DAOException {
				 
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
		} catch (SQLException e) {
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
					
					
			} catch (SQLException e) {
				throw new DAOException(e);
			}finally {
				pool.closeConnection(con, ps, rs);
			}
			return news;
		}

		
//---------------------------------Update by Id
	@Override
	public void updateById(News news, int id) throws DAOException {
		
			Connection con = null;
			PreparedStatement ps = null;

			try {

				con = pool.takeConnection();
				ps = con.prepareStatement(NEWS_UPDATE_BY_ID);

				
//!!!!!		"UPDATE news SET title = ? , brief = ? , content = ? , date = ?" + "WHERE id = ?";
				ps.setString(1, news.getTitle());
				ps.setString(2, news.getBrief());
				ps.setString(3, news.getContent());
				ps.setDate(4, Date.valueOf(news.getDate()));
				ps.setInt(5, id);
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
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
				
			} catch (SQLException e) {
				throw new DAOException(e);
			}finally {
				pool.closeConnection(con, ps);
			}		
	}
	
//-----------------------Delete Some news
	@Override
	public void deleteSome(String[] id) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		

		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(NEWS_DELETE_SOME);

//сделать цикл для массива id с удалением элементов
							
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally {
			pool.closeConnection(con, ps);
		}		
	}

}

