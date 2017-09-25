package net.post.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.post.db.PostDAO;

public class PostSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		
		PostDAO postDAO = new PostDAO();
		List postList = new ArrayList<>();
		
		String search=req.getParameter("search");
		System.out.println("search");
/*		int type = Integer.parseInt(req.getParameter("type"));*/
		int type = 1;
		
		int page = 1;
		int limit = 10;
		
		if(req.getParameter("page")!=null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		int listcount = postDAO.getSearchResultListCount(search, type, page);

		int maxpage = (int)((double)listcount/limit+0.95);
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		int endpage = maxpage;
		
		if(endpage>startpage+10-1)
			endpage = startpage + 10 - 1;
		
		postList = postDAO.getSearchResultList(search, type, page, limit);
		req.setAttribute("type", type);
		req.setAttribute("page", page);
		req.setAttribute("maxpage", maxpage);
		req.setAttribute("startpage", startpage);
		req.setAttribute("endpage", endpage);
		req.setAttribute("listcount", listcount);
		req.setAttribute("search", search);
		req.setAttribute("postList", postList); // post search result
		

			forward.setRedirect(false);
			forward.setPath("/post/post_list_main.jsp");
	

		return forward;
		
	}

}
