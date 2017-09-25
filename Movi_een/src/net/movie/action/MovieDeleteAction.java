package net.movie.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieDAO;

public class MovieDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		MovieDAO movieDAO = new MovieDAO();
		
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
		
		boolean result = false;
		
		String movieId = req.getParameter("movieid");
		result = movieDAO.deleteMovie(movieId);
		
		if(!result) {
			System.out.println("영화 삭제 실패");
			return null;
		}
		
		forward.setRedirect(true);
		forward.setPath("./movieList.mo");
		
		return forward;
	}

}
