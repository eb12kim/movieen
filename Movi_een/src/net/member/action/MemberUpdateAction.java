package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		MemberBean member = new MemberBean();
		MemberDAO memberDAO = new MemberDAO();

		boolean result = false;
		
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
		
		member.setMEMBER_ID(id);
		member.setMEMBER_PW(req.getParameter("MEMBER_PW"));
		member.setMEMBER_NAME(req.getParameter("MEMBER_NAME"));
		member.setMEMBER_PHONE(req.getParameter("MEMBER_PHONE"));
		member.setMEMBER_NATIONALITY(req.getParameter("MEMBER_NATIONALITY"));
		member.setMEMBER_MOVIE_LIKE(req.getParameterValues("MEMBER_MOVIE_LIKE"));
		member.setMEMBER_TRIP_LIKE(req.getParameterValues("MEMBER_TRIP_LIKE"));
		
		result = memberDAO.updateMember(member);
		
		if(!result) {
			System.out.println("회원 정보 수정 실패");
			return null;
		}
		
		forward.setRedirect(true);
		forward.setPath("./memberView.me?id="+id);
		
		return forward;
	}

}
