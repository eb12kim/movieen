package net.post.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.post.db.PostBean;
import net.post.db.PostDAO;
import net.post.db.ReplyBean;

public class PostDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		
		PostDAO postDAO = new PostDAO();
		PostBean post = new PostBean();
		
		int postno = Integer.parseInt(req.getParameter("postno"));
		int type = Integer.parseInt(req.getParameter("type"));

		String move = req.getParameter("move");
		int move_postno = 0;
		
		if(move!=null) {
			if(move.equals("prev")) {
				move_postno = postDAO.getPrevPost(postno, type);
			} else if(move.equals("next")) {
				move_postno = postDAO.getNextPost(postno, type);
			}
			
			if(move_postno==0) {
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('마지막 게시글 입니다!');");
				out.println("location.href='./postDetailAction.po?type="+type+"&postno="+postno+"';");
				out.println("</script>");
				out.close();
				
				return null;
			} else {
				postDAO.setReadCountUpdate(move_postno);
				post = postDAO.getDetailPost(move_postno);
			}
		} else {
			postDAO.setReadCountUpdate(postno);
			post = postDAO.getDetailPost(postno);
		}

		if(post == null) {
			System.out.println("상세내용보기 실패");
			return null;
		}
		
		req.setAttribute("postdata", post);
		
		List<ReplyBean> replyList = new ArrayList<>();

		int page = 1;
		int limit = 10;
		
		if(req.getParameter("reply_page")!=null) {
			page = Integer.parseInt(req.getParameter("reply_page"));
		}
		
		int listcount = postDAO.getReplyListCount(postno);
		
		replyList = postDAO.getReplyList(postno, page, limit);
		
		int maxpage = (int)((double)listcount/limit+0.95);
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;
		int endpage = maxpage;
		
		if(endpage>startpage+10-1)
			endpage = startpage + 10 - 1;
		
		req.setAttribute("reply_page", page);
		req.setAttribute("reply_maxpage", maxpage);
		req.setAttribute("reply_startpage", startpage);
		req.setAttribute("reply_endpage", endpage);
		req.setAttribute("reply_listcount", listcount);
		req.setAttribute("replyList", replyList);
		
		forward.setRedirect(false);
		forward.setPath("./post/post_detail.jsp");
		
		return forward;
	}

}
