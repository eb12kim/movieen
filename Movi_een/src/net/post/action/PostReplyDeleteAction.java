package net.post.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.post.db.PostDAO;

public class PostReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		PostDAO postDAO = new PostDAO();
		
		boolean result = false;
		
		HttpSession session = req.getSession();
		String loginId = (String)session.getAttribute("LoginId");
		
		String id = req.getParameter("id");
		
		if(loginId == null) {
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		if(!loginId.equals("admin") && !loginId.equals(id)) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('본인 게시글만 삭제할 수 있습니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		int replyno = Integer.parseInt(req.getParameter("replyno"));
		
		result = postDAO.deleteReply(replyno);
		
		if(!result) {
			System.out.println("댓글 삭제 실패");
			return null;
		}
		
		forward.setRedirect(true);
		forward.setPath("./postDetailAction.po?type="+req.getParameter("type")+"&postno="+req.getParameter("postno"));
		
		return forward;
	}

}
