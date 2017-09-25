package net.post.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.post.db.PostBean;
import net.post.db.PostDAO;

public class PostModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		PostDAO postDAO = new PostDAO();
		PostBean post = new PostBean();
		
		HttpSession session = req.getSession();
		String loginId = (String)session.getAttribute("LoginId");
		
		String id = req.getParameter("POST_USER_ID");

		if(loginId == null) {
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		if(!loginId.equals("admin") && !loginId.equals(id)) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('본인 게시글만 수정할 수 있습니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		boolean result = false;
		int type = Integer.parseInt(req.getParameter("POST_TYPE"));
		int postno = Integer.parseInt(req.getParameter("POST_NO"));
		
		post.setPOST_NO(postno);
		post.setPOST_TYPE(type);
		post.setPOST_SUBJECT(req.getParameter("POST_SUBJECT"));
		post.setPOST_USER_ID(id);
		post.setPOST_TEXT(req.getParameter("POST_TEXT"));
		
		if(req.getParameter("POST_FILE").equals("")||req.getParameter("POST_FILE")==null) {
            post.setPOST_FILE(null);
        } else {
            post.setPOST_FILE(req.getParameter("POST_FILE"));
        }
		
		if(type==1) {
			post.setPOST_MOVIE_ID(Integer.parseInt(req.getParameter("POST_MOVIE")));
			post.setPOST_GPS_LAT(Float.parseFloat(req.getParameter("GPS_LAT")));
			post.setPOST_GPS_LNG(Float.parseFloat(req.getParameter("GPS_LNG")));
			post.setPOST_NATION(req.getParameter("POST_NATION"));
			if(req.getParameter("POST_FILE_2").equals("")||req.getParameter("POST_FILE_2")==null) {
	            post.setPOST_FILE_2(null);
	        } else {
	            post.setPOST_FILE_2(req.getParameter("POST_FILE_2"));
	        }		
			result = postDAO.modifyMainPost(post);
		} else {
			result = postDAO.modifyFreeQnAPost(post);
		}
		
		
		if(!result) {
			System.out.println("게시글 수정 실패");
			return null;
		}
		
		forward.setRedirect(true);
		forward.setPath("./postDetailAction.po?type="+type+"&postno="+postno);
		
		return forward;
	}

}
