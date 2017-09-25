package net.post.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.movie.db.MovieDAO;
import net.post.db.PostBean;
import net.post.db.PostDAO;


public class PostListByMovieAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		PostDAO postDAO = new PostDAO();
		
		// 네비게이션
		List<String> genreList = postDAO.getGenre();
		req.setAttribute("genreList", genreList);
		
		// 리스트 출력
		List<PostBean> postList = new ArrayList<>();
		
		int page = 1;
		int limit = 6;
		int listCount = 0;
		
		if(req.getParameter("page")!=null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		if(req.getParameter("genre")!=null && req.getParameter("genre")!="") {
			String genre = req.getParameter("genre");
			String[] temp = new String[1];
			temp[0] = genre;
			
			listCount = postDAO.getMovieLikeListCount(temp);
			postList = postDAO.getMovieLikeList(temp, page, limit);
			
			req.setAttribute("genre", genre);
			req.setAttribute("movieId", null);
		} else if(req.getParameter("movieId")!=null && req.getParameter("movieId")!="") {
			int movie_id = Integer.parseInt(req.getParameter("movieId"));
			
			listCount = postDAO.getPostListByMovieCount(movie_id);
			postList = postDAO.getPostListByMovie(movie_id, page, limit);
			
			req.setAttribute("genre", null);
			req.setAttribute("movieId", movie_id);
		} else {
			listCount = postDAO.getListCount(1);
			postList = postDAO.getPostListLike(1, page, limit);
			
			req.setAttribute("genre", null);
			req.setAttribute("movieId", null);
		}
		
		int maxpage = (int)((double)listCount/limit+0.95);
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		int endpage = maxpage;
		
		if(endpage>startpage+10-1)
			endpage = startpage + 10 - 1;
		
		req.setAttribute("page", page);
		req.setAttribute("maxpage", maxpage);
		req.setAttribute("startpage", startpage);
		req.setAttribute("endpage", endpage);
		req.setAttribute("listcount",listCount);
		req.setAttribute("postList", postList);
		
		forward.setRedirect(false);
		forward.setPath("./post/post_list_by_movie.jsp");
		
		return forward;
	}

}
