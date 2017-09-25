package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;
import net.member.action.ActionForward;

public class MemberUpdateView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean member = new MemberBean();
		
		HttpSession session = req.getSession();
		String loginId = (String)session.getAttribute("LoginId");

		String id = req.getParameter("id");
		
		if(!loginId.equals("admin") && !loginId.equals(id)) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		member = memberDAO.getDetailMember(id);
		
		if(member == null) {
			System.out.println("회원 정보 수정 폼 불러오기 실패");
			return null;
		}
		
		req.setAttribute("member", member);
		
		forward.setRedirect(false);
		forward.setPath("./member/member_updateForm.jsp");
		
		return forward;
	}

}
