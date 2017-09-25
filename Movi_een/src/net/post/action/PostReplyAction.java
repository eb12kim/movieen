package net.post.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.post.db.PostDAO;
import net.post.db.ReplyBean;

public class PostReplyAction implements Action {

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

		int post_no = Integer.parseInt(req.getParameter("post_no"));
		
		ReplyBean reply = new ReplyBean();
		PostDAO postDAO = new PostDAO();
		boolean result = false;
		
		reply.setREPLY_POST_NO(post_no);
		reply.setREPLY_TEXT(req.getParameter("REPLY_TEXT"));
		reply.setREPLY_USER_ID((String)session.getAttribute("LoginId"));
		
		result = postDAO.insertReply(reply);
		
		forward.setRedirect(true);
		forward.setPath("./postDetailAction.po?type="+req.getParameter("type")+"&postno="+post_no);
		
		return forward;
	}

}
