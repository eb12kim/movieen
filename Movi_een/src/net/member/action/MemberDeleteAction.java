package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		MemberDAO memberDAO = new MemberDAO();
		
		boolean result = false;
		
		String loginId = (String)session.getAttribute("LoginId");
		
		if(loginId == null) {
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		String id = req.getParameter("id");
		
		if(id.equals("admin")) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('관리자는 탈퇴할 수 없습니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		if(!loginId.equals("admin") && !loginId.equals(id)) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('본인 계정만 탈퇴할 수 있습니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		result = memberDAO.deleteMember(id);
		
		if(!result) {
			System.out.println("회원 삭제 실패");
			return null;
		}
		
		if(loginId.equals("admin")) {
			forward.setRedirect(true);
			forward.setPath("./memberList.me");
			
			return forward;
		}
		
		forward.setRedirect(true);
		forward.setPath("./memberLogout.me");
		
		return forward;
	}

}
