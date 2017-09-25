package net.movie.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class MovieAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		MovieBean movie = new MovieBean();
		MovieDAO movieDAO = new MovieDAO();
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("LoginId");
		
/*		if(!id.equals("admin")) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다.');");
			out.println("location.href='./main.jsp';");
			out.println("</script>");
			out.close();
			
			return null;
		}*/
		
		boolean result = false;
		
		if(movieDAO.isMovie(Integer.parseInt(req.getParameter("MOVIE_ID")))){
			movie = movieDAO.getDetailMovie(Integer.parseInt(req.getParameter("MOVIE_ID")));
		} else {
		
		movie.setMOVIE_ID(Integer.parseInt(req.getParameter("MOVIE_ID")));
		movie.setMOVIE_TITLE(req.getParameter("MOVIE_TITLE"));
		movie.setMOVIE_TITLE_EN(req.getParameter("MOVIE_TITLE_EN"));
		movie.setMOVIE_PUB_YEAR(Integer.parseInt(req.getParameter("MOVIE_PUB_YEAR")));
		movie.setMOVIE_GENRE(req.getParameter("MOVIE_GENRE"));
		
		result = movieDAO.addMovie(movie);
		
		}
		
		if(!result) {
			System.out.println("영화 등록 실패");
			return null;
		}
		
		String write = req.getParameter("write");

		if(req.getParameter("write")!=null && write.equals("true")) {

			forward.setRedirect(true);
			forward.setPath("./movieList.mo?write=true");
		} else{

			forward.setRedirect(true);
			forward.setPath("./movieList.mo");
		}		
		return forward;
	}

}
