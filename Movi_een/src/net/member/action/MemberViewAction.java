package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean member = new MemberBean();
		
		String loginId = (String)session.getAttribute("LoginId");
		
		if(loginId == null) {
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		if(loginId.equals("admin") && req.getParameter("id")!=null) {
			String id = req.getParameter("id");
			member = memberDAO.getDetailMember(id);
			
			if(member == null) {
				System.out.println("회원 정보 읽기 실패");
				return null;
			}
			
			req.setAttribute("member", member);
			
			forward.setRedirect(false);
			forward.setPath("./member/member_info.jsp");
			
			return forward;
		}
		
		member = memberDAO.getDetailMember(loginId);
		
		if(member == null) {
			System.out.println("회원 정보 읽기 실패");
			return null;
		}
		
		req.setAttribute("member", member);
		
		forward.setRedirect(false);
		forward.setPath("./member/member_info.jsp");
		
		return forward;
	}

}
