package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.post.db.PostBean;
import util.JdbcUtil;

public class MemberDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			Context envCtx = (Context)init.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/mysql");

		} catch(Exception e) {
			System.out.println("DB 연결 실패 : "+e);
			return;
		}
	}
	
	public boolean joinMember(MemberBean member) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?)";
		boolean res = false;
		int result = 0;
				
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			pstmt.setString(2, member.getMEMBER_PW());
			pstmt.setString(3, member.getMEMBER_NAME());
			pstmt.setString(4, member.getMEMBER_PHONE());
			pstmt.setInt(5, member.getMEMBER_AGE());
			pstmt.setInt(6, member.getMEMBER_GENDER());
			pstmt.setString(7, member.getMEMBER_NATIONALITY());
			
			if(member.getMEMBER_MOVIE_LIKE()!=null) {
				String[] movie_like = member.getMEMBER_MOVIE_LIKE();
				String ml = "";
				for(int i=0; i<movie_like.length; i++) {
					if(i>=1) {
						ml += ",";
					}
					ml += movie_like[i];
				}
				pstmt.setString(8, ml);
			} else {
				pstmt.setString(8, null);
			}

			if(member.getMEMBER_TRIP_LIKE()!=null) {
				String[] trip_like = member.getMEMBER_TRIP_LIKE();
				String tl = "";
				for(int i=0; i<trip_like.length; i++) {
					if(i>=1) {
						tl += ",";
					}
					tl += trip_like[i];
				}
				pstmt.setString(9, tl);
			} else {
				pstmt.setString(9, null);
			}
			
			result = pstmt.executeUpdate();
			
			if(result != 0) {
				res = true;
			}
		} catch(Exception e) {
			System.out.println("joinMember 에러 : " + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return res;
	}
	
	public int isMember(MemberBean member) {
		String sql = "select password from member where id=?";
		int result = -1;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("password").equals(member.getMEMBER_PW())) {
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
		} catch(Exception e) {
			System.out.println("isMember 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
		// result 가 1이면 일치, 0이면 불일치, -1이면 id 없음
	}
	
	public int isId(String id) {
		String sql = "select * from member where id=?";
		int result = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch(Exception e) {
			System.out.println("isMember 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
		// result 가 1이면 해당 id 있음, -1이면 없음
	}
	
	public MemberBean getDetailMember(String id) {
		String sql = "select * from member where id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			
			MemberBean member = new MemberBean();
			member.setMEMBER_ID(rs.getString("id"));
			member.setMEMBER_PW(rs.getString("password"));
			member.setMEMBER_NAME(rs.getString("name"));
			member.setMEMBER_PHONE(rs.getString("phone"));
			member.setMEMBER_AGE(rs.getInt("age"));
			member.setMEMBER_GENDER(rs.getInt("gender"));
			member.setMEMBER_NATIONALITY(rs.getString("nationality"));
			
			String ml = rs.getString("movie_like");
			String tl = rs.getString("trip_like");
			
			if(ml!=null) {
				String[] movie_like = ml.split(",");
				member.setMEMBER_MOVIE_LIKE(movie_like);
			} else {
				member.setMEMBER_MOVIE_LIKE(null);
			}
			
			if(tl!=null) {
				String[] trip_like = tl.split(",");
				member.setMEMBER_TRIP_LIKE(trip_like);
			} else {
				member.setMEMBER_TRIP_LIKE(null);
			}
			
			return member;
		} catch(Exception e) {
			System.out.println("getDetailMember 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public List<MemberBean> getMemberList() {
		String sql = "select * from member";
		List<MemberBean> memberList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean member = new MemberBean();
				
				member.setMEMBER_ID(rs.getString("id"));
				member.setMEMBER_PW(rs.getString("password"));
				member.setMEMBER_NAME(rs.getString("name"));
				member.setMEMBER_PHONE(rs.getString("phone"));
				member.setMEMBER_AGE(rs.getInt("age"));
				member.setMEMBER_GENDER(rs.getInt("gender"));
				member.setMEMBER_NATIONALITY(rs.getString("nationality"));

				String ml = rs.getString("movie_like");
				String tl = rs.getString("trip_like");
				
				if(ml!=null) {
					String[] movie_like = ml.split(",");
					member.setMEMBER_MOVIE_LIKE(movie_like);
				} else {
					member.setMEMBER_MOVIE_LIKE(null);
				}
				
				if(tl!=null) {
					String[] trip_like = tl.split(",");
					member.setMEMBER_TRIP_LIKE(trip_like);
				} else {
					member.setMEMBER_TRIP_LIKE(null);
				}
				
				memberList.add(member);
			}
			
			return memberList;
		} catch(Exception e) {
			System.out.println("getMemberList 에러 : " + e);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public boolean deleteMember(String memberId) {
		String sql1 = "update post set user_id='알수없음' where user_id=?";
		String sql2 = "update reply set user_id='알수없음' where user_id=?";
		String sql3 = "update post set post_likecount=post_likecount-1 where post_no in (select post_no from heart where user_id=?)";
		String sql4 = "delete from heart where user_id=?";
		String sql5 = "delete from member where id=?";
		
		boolean result = false;
		int res1 = -1;
		int res2 = -1;
		int res3 = -1;
		int res4 = -1;
		int res5 = -1;
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, memberId);
			res1 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memberId);
			res2 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, memberId);
			res3 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql4);
			pstmt.setString(1, memberId);
			res4 = pstmt.executeUpdate();

			pstmt = conn.prepareStatement(sql5);
			pstmt.setString(1, memberId);
			res5 = pstmt.executeUpdate();
			
			if(res1>=0 && res2>=0 && res3>=0 && res4>=0 && res5>0) {
				result = true;
			}
		} catch(Exception e) {
			System.out.println("deleteMember 에러 : " + e);
		} finally {
			if(result) JdbcUtil.commit(conn);
			else JdbcUtil.rollback(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return result;
	}
	
	public boolean updateMember(MemberBean member) {
		String sql = "update member set password=?, name=?, phone=?, nationality=?, movie_like=?, trip_like=? where id=?";
		boolean res = false;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_PW());
			pstmt.setString(2, member.getMEMBER_NAME());
			pstmt.setString(3, member.getMEMBER_PHONE());
			pstmt.setString(4, member.getMEMBER_NATIONALITY());
			
			if(member.getMEMBER_MOVIE_LIKE()!=null) {
				String[] movie_like = member.getMEMBER_MOVIE_LIKE();
				String ml = "";
				for(int i=0; i<movie_like.length; i++) {
					if(i>=1) {
						ml += ",";
					}
					ml += movie_like[i];
				}
				pstmt.setString(5, ml);
			} else {
				pstmt.setString(5, null);
			}

			if(member.getMEMBER_TRIP_LIKE()!=null) {
				String[] trip_like = member.getMEMBER_TRIP_LIKE();
				String tl = "";
				for(int i=0; i<trip_like.length; i++) {
					if(i>=1) {
						tl += ",";
					}
					tl += trip_like[i];
				}
				pstmt.setString(6, tl);
			} else {
				pstmt.setString(6, null);
			}
			
			pstmt.setString(7, member.getMEMBER_ID());
			
			result = pstmt.executeUpdate();
			
			if(result>0) {
				res = true;
			}
		} catch(Exception e) {
			System.out.println("updateMember 에러 : " + e);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return res;
	}
	
}


