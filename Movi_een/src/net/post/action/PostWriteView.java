package net.post.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.post.action.ActionForward;
import net.post.db.PostBean;
import net.post.db.PostDAO;

public class PostWriteView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		String LoginId = (String)session.getAttribute("LoginId");
		
		if(LoginId==null) {
			int prevPage = (int)session.getAttribute("prevPage");

			forward.setRedirect(true);
			forward.setPath("./memberLogin.me?prevPage="+prevPage);
			
			return forward;
		}
		
		forward.setRedirect(false);
		forward.setPath("./post/post_writeForm.jsp");
		
		return forward;
	}

}
