package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		
		MemberDAO memberDAO = new MemberDAO();
		MemberBean member = new MemberBean();
		boolean result = false;
		
		member.setMEMBER_ID(req.getParameter("MEMBER_ID"));
		member.setMEMBER_PW(req.getParameter("MEMBER_PW"));
		member.setMEMBER_NAME(req.getParameter("MEMBER_NAME"));
		member.setMEMBER_PHONE(req.getParameter("MEMBER_PHONE"));
		member.setMEMBER_AGE(Integer.parseInt(req.getParameter("MEMBER_AGE")));
		member.setMEMBER_GENDER(Integer.parseInt(req.getParameter("MEMBER_GENDER")));
		member.setMEMBER_NATIONALITY(req.getParameter("MEMBER_NATIONALITY"));
		member.setMEMBER_MOVIE_LIKE(req.getParameterValues("MEMBER_MOVIE_LIKE"));
		member.setMEMBER_TRIP_LIKE(req.getParameterValues("MEMBER_TRIP_LIKE"));
		
		result = memberDAO.joinMember(member);
		
		if(!result) {
			System.out.println("회원가입 실패");
			return null;
		}
		
		forward.setRedirect(true);
		forward.setPath("./memberLogin.me");
		
		return forward;
	}

}
