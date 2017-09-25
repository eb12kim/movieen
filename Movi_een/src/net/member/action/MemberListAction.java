package net.member.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		List memberList = new ArrayList<>();
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("LoginId");
		
		if(!id.equals("admin")) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		memberList = memberDAO.getMemberList();
		
		if(memberList == null) {
			System.out.println("회원이 없습니다!");
			
			forward.setRedirect(false);
			forward.setPath("./main.jsp");
			
			return forward;
		}
		
		req.setAttribute("memberlist", memberList);
		
		forward.setRedirect(false);
		forward.setPath("./member/member_list.jsp");
		
		return forward;
	}

}
