package net.movie.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.JdbcUtil;

public class MovieDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	
	public MovieDAO() {
		try {
			Context init = new InitialContext();
			Context envCtx = (Context)init.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/mysql");
				} catch(Exception e) {
			System.out.println("DB 연결 실패 : "+e);
			return;
		}
	}
	
	public List<MovieBean> getMovieList() {
		String sql = "select * from movie order by movie_title asc";
		List<MovieBean> movieList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MovieBean movie = new MovieBean();
				
				movie.setMOVIE_ID(rs.getInt("movie_id"));
				movie.setMOVIE_TITLE(rs.getString("movie_title"));
				movie.setMOVIE_TITLE_EN(rs.getString("movie_title_en"));

				movie.setMOVIE_GENRE(rs.getString("genre"));
				movie.setMOVIE_PUB_YEAR(rs.getInt("pub_year"));
				
				movieList.add(movie);
			}
			
			return movieList;
		} catch(Exception e) {
			System.out.println("getMovieList 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public boolean isMovie(int movie_id) {
		String sql = "select * from movie where movie_id=?";
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movie_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("isMovie 에러 : " + e);
		} finally {			
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public boolean addMovie(MovieBean movie) {
		String sql = "insert into movie (movie_id, movie_title, movie_title_en, pub_year, genre) values(?,?,?,?,?)";
		int result = 0;
		int num = 0;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movie.getMOVIE_ID());
			pstmt.setString(2, movie.getMOVIE_TITLE());
			pstmt.setString(3, movie.getMOVIE_TITLE_EN());
			pstmt.setInt(4, movie.getMOVIE_PUB_YEAR());
			pstmt.setString(5, movie.getMOVIE_GENRE());
			
			result = pstmt.executeUpdate();
			
			if(result!=0) {
				return true;
			}
		} catch(Exception e) {
			System.out.println("addMovie 에러 : " + e);
		} finally {			
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return false;
	}
	
	public MovieBean getDetailMovie(int movieid) {
		String sql = "select * from movie where movie_id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				MovieBean movie = new MovieBean();
			
				movie.setMOVIE_ID(rs.getInt("movie_id"));
				movie.setMOVIE_TITLE(rs.getString("movie_title"));
				movie.setMOVIE_TITLE_EN(rs.getString("movie_title_en"));
				movie.setMOVIE_PUB_YEAR(rs.getInt("pub_year"));
				movie.setMOVIE_GENRE(rs.getString("genre"));
			
				return movie;
			}
		} catch(Exception e) {
			System.out.println("getDetailMovie 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public boolean deleteMovie(String movieid) {
		String sql1 = "delete from heart where post_no in (select post_no from post where movie_id=?)";
		String sql2 = "delete from reply where post_no in (select post_no from post where movie_id=?)";
		String sql3 = "delete from post where movie_id=?";
		String sql4 = "delete from movie where movie_id=?";
		
		boolean result = false;
		int result1 = -1;
		int result2 = -1;
		int result3 = -1;
		int result4 = -1;
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, movieid);
			result1 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, movieid);
			result2 = pstmt.executeUpdate();

			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, movieid);
			result3 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql4);
			pstmt.setString(1, movieid);
			result4 = pstmt.executeUpdate();
			
			if(result1>=0 && result2>=0 && result3>=0 && result4>0) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("deleteMovie 에러 : " + e);
		} finally {
			if(result) JdbcUtil.commit(conn);
			else JdbcUtil.rollback(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
}
