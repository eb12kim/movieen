package net.movie.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieDAO;

public class MovieListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("LoginId");
		String write = req.getParameter("write");
		
		MovieDAO movieDAO = new MovieDAO();
		List movieList = new ArrayList<>();
		
		movieList = movieDAO.getMovieList();
		
		if(movieList == null) {
			System.out.println("등록된 영화가 없습니다!");
			return null;
		}
		
		req.setAttribute("movielist", movieList);
		
		forward.setRedirect(false);
	
		if(req.getParameter("write")!=null && write.equals("true")) {
			forward.setPath("./post/post_movieSelect.jsp");

		} else {		
		forward.setPath("./movie/movie_list.jsp");
		}
		return forward;
	}

}
