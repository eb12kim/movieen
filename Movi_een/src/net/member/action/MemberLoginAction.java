package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean member = new MemberBean();
		
		int result = -1;
		
		member.setMEMBER_ID(req.getParameter("MEMBER_ID"));
		member.setMEMBER_PW(req.getParameter("MEMBER_PW"));
		result = memberDAO.isMember(member);
		
		if(result == 0) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("location.href='./memberLogin.me';");
			out.println("</script>");
			out.close();
			
			return null;
		} else if(result == -1) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.');");
			out.println("location.href='./memberLogin.me';");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		session.setAttribute("LoginId", member.getMEMBER_ID());
		
		if(session.getAttribute("Page")!=null && session.getAttribute("Page").equals("detail")) {
			forward.setRedirect(true);
			forward.setPath("./postDetailAction.po?type="+session.getAttribute("detail_type")+"&postno="+session.getAttribute("detail_no"));
			
			return forward;
		}
		
		if(session.getAttribute("prevPage")!=null) {
			int prevPage = (int)session.getAttribute("prevPage");
			
			if(prevPage==1||prevPage==2||prevPage==3) {
				forward.setRedirect(true);
				forward.setPath("./postListAction.po?type="+prevPage);
				
				return forward;
			}
		}
		
		forward.setRedirect(false);
		forward.setPath("./main.jsp");
		
		return forward;
	}

}
