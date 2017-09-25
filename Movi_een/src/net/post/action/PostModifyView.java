package net.post.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.post.db.PostBean;
import net.post.db.PostDAO;

public class PostModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		PostDAO postDAO = new PostDAO();
		PostBean post = new PostBean();
		
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
			out.println("alert('본인 게시글만 수정할 수 있습니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		int postno = Integer.parseInt(req.getParameter("postno"));
		post = postDAO.getDetailPost(postno);
		
		if(post==null) {
			System.out.println("게시글 수정 폼 불러오기 실패");
			return null;
		}
		
		req.setAttribute("post", post);
		
		forward.setRedirect(false);
		forward.setPath("./post/post_modifyForm.jsp");
		
		return forward;
	}

}
