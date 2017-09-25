package net.movie.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;


public class MovieViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		MovieDAO movieDAO = new MovieDAO();
		MovieBean movie = new MovieBean();
		
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
		
		int movieId = Integer.parseInt(req.getParameter("movieid"));
		movie = movieDAO.getDetailMovie(movieId);
		
		if(movie == null) {
			System.out.println("영화 정보 불러오기 실패");
			return null;
		}
		
		req.setAttribute("movie", movie);
		
		forward.setRedirect(false);
		forward.setPath("./movie/movie_info.jsp");
		
		return forward;
	}

}
