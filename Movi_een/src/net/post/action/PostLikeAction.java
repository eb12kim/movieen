package net.post.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.post.db.PostDAO;

public class PostLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("LoginId")==null) {
			forward.setRedirect(true);
			forward.setPath("./memberLogin.me");
			
			return forward;
		}

		PostDAO postDAO = new PostDAO();

		boolean result = false;
		boolean temp = false;

		
		int type = Integer.parseInt(req.getParameter("type"));
		int post_no = Integer.parseInt(req.getParameter("post_no"));
		String user_id = (String)session.getAttribute("LoginId");
		String sort = req.getParameter("sort");
		
		if(sort==null) {
			sort = "";
		}

		temp = postDAO.selectLike(user_id, post_no);
		
		if(temp) {
			result = postDAO.deleteLike(user_id, post_no);
		} else {
			result = postDAO.insertLike(user_id,post_no);
		}
		
		if(!result) {
			System.out.println("좋아요/좋아요 취소 실패");
		}
		
		if(session.getAttribute("Page")!=null) {
			String likePage = (String)session.getAttribute("Page");
			
			if(likePage.equals("list")) {
				forward.setRedirect(true);
				forward.setPath("./postListAction.po?type="+type+"&sort="+sort);
				
				return forward;
			} else if(likePage.equals("detail")) {
				forward.setRedirect(true);
				forward.setPath("./postDetailAction.po?type="+type+"&postno="+post_no);
				
				return forward;
			}
		}
		
		forward.setRedirect(true);
		forward.setPath("./postListAction.po?type="+type+"&sort="+sort);
	
		return forward;
	}

}