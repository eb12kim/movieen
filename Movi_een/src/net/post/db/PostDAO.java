package net.post.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.JdbcUtil;

public class PostDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	
	public PostDAO() {
		try {
			Context init = new InitialContext();
			Context envCtx = (Context)init.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/mysql");

		} catch(Exception e) {
			System.out.println("DB 연결 실패 : "+e);
			return;
		}
	}
	
	public int getListCount(int type) {
		String sql = "select count(*) from post where post_type=?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getListCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getPostList(int type, int page, int limit) {
		String sql = "select * from post where post_type=? order by post_no desc limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getPostList 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public List<PostBean> getPostListRead(int type, int page, int limit) {
		String sql = "select * from post where post_type=? order by post_readcount desc limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
			
		} catch(Exception e) {
			System.out.println("getPostListRead 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public List<PostBean> getPostListLike(int type, int page, int limit) {
		String sql = "select * from post where post_type=? order by post_likecount desc limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));			
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getPostListLike 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public boolean writeMainPost(PostBean post) {
		String sql = "insert into post (post_type, post_subject, user_id, post_text, post_file, post_file_2, movie_id, post_gps_lat, post_gps_lng, post_nation, post_date, post_readcount, post_likecount) "
				+ " values(?,?,?,?,?,?,?,?,?,?,sysdate(),?,?)";
		int res = 0;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post.getPOST_TYPE());
			pstmt.setString(2, post.getPOST_SUBJECT());
			pstmt.setString(3, post.getPOST_USER_ID());
			pstmt.setString(4, post.getPOST_TEXT());
			pstmt.setString(5, post.getPOST_FILE());
			pstmt.setString(6, post.getPOST_FILE_2());
			pstmt.setInt(7, post.getPOST_MOVIE_ID());
			pstmt.setFloat(8, post.getPOST_GPS_LAT());
			pstmt.setFloat(9, post.getPOST_GPS_LNG());
			pstmt.setString(10, post.getPOST_NATION());
			pstmt.setInt(11, 0);
			pstmt.setInt(12, 0);
			
			res = pstmt.executeUpdate();
			
			if(res>0) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("writeMainPost 에러 : " + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public boolean writeFreeQnAPost(PostBean post) {
		String sql = "insert into post (post_type, post_subject, user_id, post_text, post_file, post_date, post_readcount, post_likecount) "
				+ " values(?,?,?,?,?,sysdate(),?,?)";
		int res = 0;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post.getPOST_TYPE());
			pstmt.setString(2, post.getPOST_SUBJECT());
			pstmt.setString(3, post.getPOST_USER_ID());
			pstmt.setString(4, post.getPOST_TEXT());
			pstmt.setString(5, post.getPOST_FILE());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			
			res = pstmt.executeUpdate();
			
			if(res>0) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("writeFreeQnAPost 에러 : " + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public PostBean getDetailPost(int no) {
		String sql = "select * from post where post_no=?";
		PostBean post = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				post = new PostBean();
				
				post.setPOST_NO(no);
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
			}
		} catch(Exception e) {
			System.out.println("getDetail 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return post;
	}
	
	public int getPrevPost(int no, int type) {
		String sql = "select max(post_no) from post where post_type=? and post_no<?";
		int result = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getPrevPost 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public int getNextPost(int no, int type) {
		String sql = "select min(post_no) from post where post_type=? and post_no>?";
		int result = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getNextPost 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public boolean deletePost(int postno) {
		String sql1 = "delete from heart where post_no=?";
		String sql2 = "delete from reply where post_no=?";
		String sql3 = "delete from post where post_no=?";
		boolean result = false;
		int result1 = -1;
		int result2 = -1;
		int result3 = -1;
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, postno);
			result1 = pstmt.executeUpdate();

			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, postno);
			result2 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, postno);
			result3 = pstmt.executeUpdate();
			
			if(result1>=0 && result2>=0 && result3>0) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("deletePost 에러 : " + e);
		} finally {
			if(result) JdbcUtil.commit(conn);
			else JdbcUtil.rollback(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public boolean modifyMainPost(PostBean post) {
		String sql = "update post set post_type=?, post_subject=?, post_text=?, post_file=?, post_file_2=?, movie_id=?, post_gps_lat=?, post_gps_lng=?, post_nation=? where post_no=?";
		boolean result = false;
		int res;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, post.getPOST_TYPE());
			pstmt.setString(2, post.getPOST_SUBJECT());
			pstmt.setString(3, post.getPOST_TEXT());
			pstmt.setString(4, post.getPOST_FILE());
			pstmt.setString(5, post.getPOST_FILE_2());
			pstmt.setInt(6, post.getPOST_MOVIE_ID());
			pstmt.setFloat(7, post.getPOST_GPS_LAT());
			pstmt.setFloat(8, post.getPOST_GPS_LNG());
			pstmt.setString(9, post.getPOST_NATION());
			pstmt.setInt(10, post.getPOST_NO());
			
			res = pstmt.executeUpdate();
			
			if(res>0) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("modifyPost 에러 : " + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public boolean modifyFreeQnAPost(PostBean post) {
		String sql = "update post set post_type=?, post_subject=?, post_text=?, post_file=? where post_no=?";
		boolean result = false;
		int res;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, post.getPOST_TYPE());
			pstmt.setString(2, post.getPOST_SUBJECT());
			pstmt.setString(3, post.getPOST_TEXT());
			pstmt.setString(4, post.getPOST_FILE());
			pstmt.setInt(5, post.getPOST_NO());
			
			res = pstmt.executeUpdate();
			
			if(res>0) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("modifyPost 에러 : " + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public void setReadCountUpdate(int num) {
		String sql = "update post set post_readcount=post_readcount+1 where post_no=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("setReadCountUpdate 에러 : " + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public boolean selectLike(String user_id, int post_no) {
		String sql = "select count(*) from heart where post_no=? and user_id=?";
		boolean result = false;
		int res = 0;
		
		try {
			 conn = ds.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, post_no);
			 pstmt.setString(2, user_id);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				res = rs.getInt(1);
			 }
			 
			 if(res>0) {
				 result = true;
			 }
		} catch(Exception e) {
			System.out.println("selectLike 에러 : " + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public boolean insertLike(String user_id, int post_no) {
		String sql1 = "insert into heart (post_no, user_id) values(?,?)";
		String sql2 = "update post set post_likecount=post_likecount+1 where post_no=?";
		int rs1 = 0;
		int rs2 = 0;
		boolean res1 = false;
		boolean res2 = false;		
		boolean result = false;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, post_no);
			pstmt.setString(2, user_id);
			rs1 = pstmt.executeUpdate();
			if(rs1>0) {
				res1 = true;
			}
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, post_no);
			rs2 = pstmt.executeUpdate();
			if(rs2>0) {
				res2 = true;
			}
			
			if(res1&&res2) {
				result = true;
			}
			
		} catch(Exception e) {
			System.out.println("insertLike 에러  :" + e);
		} finally {
			if(!result) {
				JdbcUtil.rollback(conn);
			} else {
				JdbcUtil.commit(conn);
			}
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public boolean deleteLike(String user_id, int post_no ) {
		String sql1 = "delete from heart where post_no=? and user_id=?";
		String sql2 = "update post set post_likecount=post_likecount-1 where post_no=?";
		int rs1 = 0;
		int rs2 = 0;
		boolean res1 = false;
		boolean res2 = false;		
		boolean result = false;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, post_no);
			pstmt.setString(2, user_id);
			rs1 = pstmt.executeUpdate();
			if(rs1>0) {
				res1 = true;
			}
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, post_no);
			rs2 = pstmt.executeUpdate();
			if(rs2>0) {
				res2 = true;
			}
			
			if(res1&&res2) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("deleteLike 에러  :" + e);
		} finally {
			if(!result) {
				JdbcUtil.rollback(conn);
			} else {
				JdbcUtil.commit(conn);
			}
			
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public boolean insertReply(ReplyBean reply) {
		String sql = "insert into reply (post_no, reply_lev, reply_text, user_id, reply_date) values(?, ?, ?, ?, sysdate())";
		String sql1 = "select max(reply_lev) from reply where post_no=?";
		int reply_lev = 0;
		int res = 0;
		boolean result = false;

		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, reply.getREPLY_POST_NO());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reply_lev = rs.getInt(1) + 1;
			} else {
				reply_lev = 1;
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getREPLY_POST_NO());
			pstmt.setInt(2, reply_lev);
			pstmt.setString(3, reply.getREPLY_TEXT());
			pstmt.setString(4, reply.getREPLY_USER_ID());
			res = pstmt.executeUpdate();
			
			if(res>0) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("insertReply 에러  :" + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public int getReplyListCount(int post_no) {
		String sql = "select count(*) from reply where post_no=?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getReplyListCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<ReplyBean> getReplyList(int post_no, int page, int limit) {
		String sql = "select * from reply where post_no=? order by reply_lev asc limit ?,?";
		List<ReplyBean> replyList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, post_no);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReplyBean reply = new ReplyBean();
				
				reply.setREPLY_NO(rs.getInt("reply_no"));
				reply.setREPLY_POST_NO(rs.getInt("post_no"));
				reply.setREPLY_LEV(rs.getInt("reply_lev"));
				reply.setREPLY_TEXT(rs.getString("reply_text"));
				reply.setREPLY_USER_ID(rs.getString("user_id"));
				reply.setREPLY_DATE(rs.getDate("reply_date"));
				
				replyList.add(reply);
			}
			
			return replyList;
		} catch(Exception e) {
			System.out.println("getReplyList 에러  :" + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public boolean deleteReply(int reply_no) {
		String sql = "delete from reply where reply_no=?";
		int res = 0;	
		boolean result = false;

		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply_no);
			res = pstmt.executeUpdate();
			
			if(res>0) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("deleteReply 에러  :" + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public int getMyListCount(String user_id) {
		String sql = "select count(*) from post where user_id=?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getMyListCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getMyList(String user_id, int page, int limit) {
		String sql = "select * from post where user_id=? order by post_no desc limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getMyList 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public int getMyReplyListCount(String user_id) {
		String sql = "select count(*) from reply where user_id=?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getMyReplyListCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<ReplyBean> getMyReplyList(String user_id, int page, int limit) {
		String sql = "select * from reply where user_id=? order by reply_no desc limit ?,?";
		List<ReplyBean> replyList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReplyBean reply = new ReplyBean();
				
				reply.setREPLY_NO(rs.getInt("reply_no"));
				reply.setREPLY_POST_NO(rs.getInt("post_no"));
				reply.setREPLY_LEV(rs.getInt("reply_lev"));
				reply.setREPLY_TEXT(rs.getString("reply_text"));
				reply.setREPLY_USER_ID(rs.getString("user_id"));
				reply.setREPLY_DATE(rs.getDate("reply_date"));
				
				replyList.add(reply);
			}
			
			return replyList;
		} catch(Exception e) {
			System.out.println("getMyReplyList 에러  :" + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public int getGetLikeCount(String user_id) {
		String sql = "select sum(post_likecount) from post where user_id=?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getGetLikeCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public int getMyLikeListCount(String user_id) {
		String sql = "select count(*) from heart where user_id=?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getMyLikeListCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getMyLikeList(String user_id, int page, int limit) {
		String sql = "select * from post where post_no in (select post_no from heart where user_id=?) order by post_no desc limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getMyLikeList 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	/* 170831 *//////////////////////////////////////////////////////////////////////////////////////////////
	public int getSearchResultListCount(String search, int type, int page) {
		String sql = "select count(*) from post where post_nation like ? or movie_id in (select movie_id from movie where movie_title like ?) order by post_likecount desc";
		int x = 0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getSearchResultListCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getSearchResultList(String search, int type, int page, int limit) {
		String sql = "select * from post where post_nation like ? or movie_id in (select movie_id from movie where movie_title like ?) order by post_likecount desc limit ?,?";
		
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getPostList 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	/////////////////////////////// 수정 170831
	public int getMovieLikeListCount(String[] movieLike) {
		String sql = "select count(*) from post where movie_id in (select movie_id from movie where genre in (?,?,?)) order by post_likecount desc";
		List<PostBean> postList = new ArrayList<>();
		String m1 = null;
		String m2 = null;
		String m3 = null;
		
		if(movieLike!=null) {
			if(movieLike.length>=1 && movieLike[0]!=null && movieLike[0]!="")
				m1 = movieLike[0];
			if(movieLike.length>=2 && movieLike[1]!=null && movieLike[1]!="")
				m2 = movieLike[1];
			if(movieLike.length>=3 && movieLike[2]!=null && movieLike[2]!="")
				m3 = movieLike[2];
		}
		int x = 0;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m1);
			pstmt.setString(2, m2);
			pstmt.setString(3, m3);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getMovieLikeListCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getMovieLikeList(String[] movieLike, int page, int limit) {
		String sql = "select * from post where movie_id in (select movie_id from movie where genre in (?,?,?)) order by post_likecount desc limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		String m1 = null;
		String m2 = null;
		String m3 = null;
		
		if(movieLike!=null) {
			if(movieLike.length>=1 && movieLike[0]!=null && movieLike[0]!="")
				m1 = movieLike[0];
			if(movieLike.length>=2 && movieLike[1]!=null && movieLike[1]!="")
				m2 = movieLike[1];
			if(movieLike.length>=3 && movieLike[2]!=null && movieLike[2]!="")
				m3 = movieLike[2];
		}
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m1);
			pstmt.setString(2, m2);
			pstmt.setString(3, m3);
			pstmt.setInt(4, startrow);
			pstmt.setInt(5, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getMovieLikeList 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public int getTripLikeListCount(String[] tripLike) {
		String sql = "select count(*) from post where post_nation in (?,?,?) order by post_likecount desc";
		List<PostBean> postList = new ArrayList<>();
		String t1 = null;
		String t2 = null;
		String t3 = null;
		
		if(tripLike!=null) {
			if(tripLike.length>=1 && tripLike[0]!=null && tripLike[0]!="")
				t1 = tripLike[0];
			if(tripLike.length>=2 && tripLike[1]!=null && tripLike[1]!="")
				t2 = tripLike[1];
			if(tripLike.length>=3 && tripLike[2]!=null && tripLike[2]!="")
				t3 = tripLike[2];
		}
		int x = 0;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t1);
			pstmt.setString(2, t2);
			pstmt.setString(3, t3);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getTripLikeListCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getTripLikeList(String[] tripLike, int page, int limit) {
		String sql = "select * from post where post_nation in (?,?,?) order by post_likecount desc limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		String t1 = null;
		String t2 = null;
		String t3 = null;
		
		if(tripLike!=null) {
			if(tripLike.length>=1 && tripLike[0]!=null && tripLike[0]!="")
				t1 = tripLike[0];
			if(tripLike.length>=2 && tripLike[1]!=null && tripLike[1]!="")
				t2 = tripLike[1];
			if(tripLike.length>=3 && tripLike[2]!=null && tripLike[2]!="")
				t3 = tripLike[2];
		}
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t1);
			pstmt.setString(2, t2);
			pstmt.setString(3, t3);
			pstmt.setInt(4, startrow);
			pstmt.setInt(5, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getTripLikeList 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	//구글맵 ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public List<MapBean> initMap() {
        String sql = "select * from post, movie where post.movie_id = movie.movie_id";
        List<MapBean> mapList = new ArrayList<>();
        
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                MapBean map = new MapBean();
                
                map.setPOST_NO(rs.getInt("post_no"));
                map.setPOST_SUBJECT(rs.getString("post_subject"));
                map.setPOST_MOVIE_ID(rs.getInt("movie_id"));
                map.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
                map.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
                map.setPOST_NATION(rs.getString("post_nation"));
                map.setMOVIE_TITLE(rs.getString("movie_title"));
                map.setPOST_FILE(rs.getString("post_file"));
                
                
                mapList.add(map);
            }
            
            return mapList;
        } catch(Exception e) {
            System.out.println("getPostList 에러 : " + e);
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }
        
        return null;
    }
    
    
    
    public List<MapBean> mapByNation(String nation) {
        String sql = "select * from post, movie where post.movie_id = movie.movie_id and post_nation=?" ;
        List<MapBean> mapList = new ArrayList<>();
        
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nation);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                MapBean map = new MapBean();
                
                map.setPOST_NO(rs.getInt("post_no"));
                map.setPOST_SUBJECT(rs.getString("post_subject"));
                map.setPOST_MOVIE_ID(rs.getInt("movie_id"));
                map.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
                map.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
                map.setPOST_NATION(rs.getString("post_nation"));
                map.setMOVIE_TITLE(rs.getString("movie_title"));
                
                mapList.add(map);
            }
            
            return mapList;
        } catch(Exception e) {
            System.out.println("getPostList 에러 : " + e);
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }
        
        return null;
    }
    
    public List<MapBean> mapByMovie(int movie_id) {
        String sql = "select * from post a, movie b where a.movie_id = b.movie_id and b.movie_id=?" ;
        List<MapBean> mapList = new ArrayList<>();
        
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movie_id);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                MapBean map = new MapBean();
                
                map.setPOST_NO(rs.getInt("post_no"));
                map.setPOST_SUBJECT(rs.getString("post_subject"));
                map.setPOST_MOVIE_ID(rs.getInt("movie_id"));
                map.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
                map.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
                map.setPOST_NATION(rs.getString("post_nation"));
                map.setMOVIE_TITLE(rs.getString("movie_title"));
                
                mapList.add(map);
            }
            
            return mapList;
        } catch(Exception e) {
            System.out.println("getPostList 에러 : " + e);
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }
        
        return null;
    }
    
    //구글맵 end //
    
    // 수진 170831 2차
	public int getPostListByMovieCount(int movie_id) {
		String sql = "select count(*) from post where movie_id=?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movie_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getPostListByMovieCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getPostListByMovie(int movie_id, int page, int limit) {
		String sql = "select * from post where movie_id=? limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movie_id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getPostListByMovie 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public int getPostByGenreCount(String genre) {
		String sql = "select count(*) from post where movie_id in (select movie_id from movie where genre=?)";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, genre);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getPostByGenreCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<String> getGenre() {
		String sql = "select distinct genre from movie where movie_id in (select movie_id from post)";
		List<String> genreList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String genre = rs.getString(1);
				genreList.add(genre);
			}
			
			return genreList;
		} catch(Exception e) {
			System.out.println("getGenre 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public int getPostByMovieCount(int movie_id) {
		String sql = "select count(*) from post where movie_id=?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movie_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getPostByMovieCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<Integer> getMovieByGenre(String genre) {
		String sql = "select distinct movie_id from movie where movie_id in (select movie_id from post) AND genre=?";
		List<Integer> movieList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, genre);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int movie_id = rs.getInt(1);
				movieList.add(movie_id);
			}
			
			return movieList;
		} catch(Exception e) {
			System.out.println("getGenre 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public int getPostListByLandCount(String[] land) {
		String sql = "select count(*) from post where post_nation in (?,?,?,?,?,?,?)";
		int x = 0;
		
		String l1 = null;
		String l2 = null;
		String l3 = null;
		String l4 = null;
		String l5 = null;
		String l6 = null;
		String l7 = null;
		
		if(land!=null) {
			if(land.length>=1 && land[0]!=null && land[0]!="")
				l1 = land[0];
			if(land.length>=2 && land[1]!=null && land[1]!="")
				l2 = land[1];
			if(land.length>=3 && land[2]!=null && land[2]!="")
				l3 = land[2];
			if(land.length>=4 && land[3]!=null && land[3]!="")
				l4 = land[3];
			if(land.length>=5 && land[4]!=null && land[4]!="")
				l5 = land[4];
			if(land.length>=6 && land[5]!=null && land[5]!="")
				l6 = land[5];
			if(land.length>=7 && land[6]!=null && land[6]!="")
				l7 = land[6];
		}
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l1);
			pstmt.setString(2, l2);
			pstmt.setString(3, l3);
			pstmt.setString(4, l4);
			pstmt.setString(5, l5);
			pstmt.setString(6, l6);
			pstmt.setString(7, l7);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getPostListByLandCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getPostListByLand(String[] land, int page, int limit) {
		String sql = "select * from post where post_nation in (?,?,?,?,?,?,?) limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		String l1 = null;
		String l2 = null;
		String l3 = null;
		String l4 = null;
		String l5 = null;
		String l6 = null;
		String l7 = null;
		
		if(land!=null) {
			if(land.length>=1 && land[0]!=null && land[0]!="")
				l1 = land[0];
			if(land.length>=2 && land[1]!=null && land[1]!="")
				l2 = land[1];
			if(land.length>=3 && land[2]!=null && land[2]!="")
				l3 = land[2];
			if(land.length>=4 && land[3]!=null && land[3]!="")
				l4 = land[3];
			if(land.length>=5 && land[4]!=null && land[4]!="")
				l5 = land[4];
			if(land.length>=6 && land[5]!=null && land[5]!="")
				l6 = land[5];
			if(land.length>=7 && land[6]!=null && land[6]!="")
				l7 = land[6];
		}
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l1);
			pstmt.setString(2, l2);
			pstmt.setString(3, l3);
			pstmt.setString(4, l4);
			pstmt.setString(5, l5);
			pstmt.setString(6, l6);
			pstmt.setString(7, l7);
			pstmt.setInt(8, startrow);
			pstmt.setInt(9, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getPostListByLand 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}

	public int getPostListByLandCount_Etc() {
		String sql = "select count(*) from post where post_nation not in ('대한민국','대만','베트남','일본','중국','태국','홍콩','미국','캐나다','독일','스위스','영국','오스트리아','이탈리아','크로아티아','프랑스')";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getPostListByLandCount_Etc 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getPostListByLand_Etc(int page, int limit) {
		String sql = "select * from post where post_nation not in ('대한민국','대만','베트남','일본','중국','태국','홍콩','미국','캐나다','독일','스위스','영국','오스트리아','이탈리아','크로아티아','프랑스') limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getPostListByLand_Etc 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public int getPostListByNationCount(String nation) {
		String sql = "select count(*) from post where post_nation=?";
		int x = 0;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nation);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getPostListByNationCount 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	public List<PostBean> getPostListByNation(String nation, int page, int limit) {	
		String sql = "select * from post where post_nation=? limit ?,?";
		List<PostBean> postList = new ArrayList<>();
		int startrow = (page-1)*limit;
		int endrow = limit;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nation);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PostBean post = new PostBean();
				
				post.setPOST_NO(rs.getInt("post_no"));
				post.setPOST_TYPE(rs.getInt("post_type"));
				post.setPOST_SUBJECT(rs.getString("post_subject"));
				post.setPOST_USER_ID(rs.getString("user_id"));
				post.setPOST_TEXT(rs.getString("post_text"));
				post.setPOST_FILE(rs.getString("post_file"));
				post.setPOST_FILE_2(rs.getString("post_file_2"));
				post.setPOST_MOVIE_ID(rs.getInt("movie_id"));
				post.setPOST_GPS_LAT(rs.getFloat("post_gps_lat"));
				post.setPOST_GPS_LNG(rs.getFloat("post_gps_lng"));
				post.setPOST_NATION(rs.getString("post_nation"));
				post.setPOST_DATE(rs.getDate("post_date"));
				post.setPOST_READCOUNT(rs.getInt("post_readcount"));
				post.setPOST_LIKECOUNT(rs.getInt("post_likecount"));
				
				postList.add(post);
			}
			
			return postList;
		} catch(Exception e) {
			System.out.println("getPostListByNation 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public List<String> getNationByLand(String[] land) {
		String sql = "select distinct post_nation from post where post_nation in (?,?,?,?,?,?,?)";
		List<String> nationList = new ArrayList<>();
		
		String l1 = null;
		String l2 = null;
		String l3 = null;
		String l4 = null;
		String l5 = null;
		String l6 = null;
		String l7 = null;
		
		if(land!=null) {
			if(land.length>=1 && land[0]!=null && land[0]!="")
				l1 = land[0];
			if(land.length>=2 && land[1]!=null && land[1]!="")
				l2 = land[1];
			if(land.length>=3 && land[2]!=null && land[2]!="")
				l3 = land[2];
			if(land.length>=4 && land[3]!=null && land[3]!="")
				l4 = land[3];
			if(land.length>=5 && land[4]!=null && land[4]!="")
				l5 = land[4];
			if(land.length>=6 && land[5]!=null && land[5]!="")
				l6 = land[5];
			if(land.length>=7 && land[6]!=null && land[6]!="")
				l7 = land[6];
		}
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, l1);
			pstmt.setString(2, l2);
			pstmt.setString(3, l3);
			pstmt.setString(4, l4);
			pstmt.setString(5, l5);
			pstmt.setString(6, l6);
			pstmt.setString(7, l7);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String nation = rs.getString(1);
				nationList.add(nation);
			}
			
			return nationList;
		} catch(Exception e) {
			System.out.println("getNationByLand 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public List<String> getNationByLandEtc() {
		String sql = "select distinct post_nation from post where post_nation not in ('대한민국','대만','베트남','일본','중국','태국','홍콩','미국','캐나다','독일','스위스','영국','오스트리아','이탈리아','크로아티아','프랑스')";
		List<String> nationList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String nation = rs.getString(1);
				nationList.add(nation);
			}
			
			return nationList;
		} catch(Exception e) {
			System.out.println("getNationByLandEtc 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
}


