package net.post.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.post.db.PostBean;
import net.post.db.PostDAO;

public class PostWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		PostDAO postDAO = new PostDAO();
		PostBean post = new PostBean();
		ActionForward forward = new ActionForward();
		
		boolean result = false;
		int type = Integer.parseInt(req.getParameter("POST_TYPE"));
		
		post.setPOST_TYPE(type);
		post.setPOST_SUBJECT(req.getParameter("POST_SUBJECT"));
		post.setPOST_USER_ID(req.getParameter("POST_USER_ID"));
		post.setPOST_TEXT(req.getParameter("POST_TEXT"));

		if(req.getParameter("POST_FILE").equals("")||req.getParameter("POST_FILE")==null) {
            post.setPOST_FILE(null);
            System.out.println("null로 바꿈");
        } else {
            post.setPOST_FILE(req.getParameter("POST_FILE"));
            System.out.println("null로 바꾸지 않고 DB로 저장됨");
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
			result = postDAO.writeMainPost(post);
		} else {
			result = postDAO.writeFreeQnAPost(post);
		}
		
		if(!result) {
			System.out.println("글쓰기 실패");
			return null;
		}
		
		forward.setRedirect(true);
		forward.setPath("./postListAction.po?type="+type);
		
		return forward;
	}

}
