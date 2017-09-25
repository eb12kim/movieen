package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		session.setAttribute("LoginId", null);
		
		forward.setRedirect(false);
		forward.setPath("./main.jsp");
		
		return forward;
	}

}
