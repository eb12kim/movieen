package net.post.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.post.db.PostBean;
import net.post.db.PostDAO;

public class PostListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		
		PostDAO postDAO = new PostDAO();
		List postList = new ArrayList<>();
		
		String sort = req.getParameter("sort");
		
		if(req.getParameter("sort")==null) {
			sort = "";
		}
		
		int type = Integer.parseInt(req.getParameter("type"));
		
		int page = 1;
		int limit = 10;
		
		if(req.getParameter("page")!=null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		int listcount = postDAO.getListCount(type);

		
		if(sort=="") {
			postList = postDAO.getPostList(type, page, limit);
		} else if(sort.equals("readcount")) {
			postList = postDAO.getPostListRead(type, page, limit);
		} else if(sort.equals("likecount")){
			postList = postDAO.getPostListLike(type, page, limit);
		}
		
		int maxpage = (int)((double)listcount/limit+0.95);
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		int endpage = maxpage;
		
		if(endpage>startpage+10-1)
			endpage = startpage + 10 - 1;
		
		req.setAttribute("sort", sort);
		req.setAttribute("type", type);
		req.setAttribute("page", page);
		req.setAttribute("maxpage", maxpage);
		req.setAttribute("startpage", startpage);
		req.setAttribute("endpage", endpage);
		req.setAttribute("listcount", listcount);
		req.setAttribute("postList", postList);
		
		if(type==1){
			forward.setRedirect(false);
			forward.setPath("/post/post_list_main.jsp");
	
		} else if (type==2){
			forward.setRedirect(false);
			forward.setPath("/post/post_list_free.jsp");	
		} else {
			forward.setRedirect(false);
			forward.setPath("/post/post_list_qna.jsp");	
		}
				
		return forward;
		
	}

}
