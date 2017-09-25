package net.post.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.post.action.Action;
import net.post.action.ActionForward;

public class PostFrontController extends HttpServlet implements Servlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doProcess(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doProcess(req, res);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String RequestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/postListAction.po")) {
			action = new PostListAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postWrite.po")) {
			action = new PostWriteView();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postWriteAction.po")) {
			action = new PostWriteAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postDetailAction.po")) {
			action = new PostDetailAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postDeleteAction.po")) {
			action = new PostDeleteAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postModifyView.po")) {
			action = new PostModifyView();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postModifyAction.po")) {
			action = new PostModifyAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}  else if(command.equals("/postLikeAction.po")) {
			action = new PostLikeAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postReplyAction.po")) {
			action = new PostReplyAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postReplyDeleteAction.po")) {
			action = new PostReplyDeleteAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
			/* 메인창에서 검색하는 부분 추가 by은별 170831 */
		} else if(command.equals("/postSearchAction.po")) {
			action = new PostSearchAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postListByMovieAction.po")) {
			action = new PostListByMovieAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postListByNationAction.po")) {
			action = new PostListByNationAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/postByMapAction.po")) {
            action = new PostByMapAction();
            
            try {
                forward = action.execute(req, res);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
		
		if(forward != null) {
			if(forward.isRedirect()) {
				res.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher rd = req.getRequestDispatcher(forward.getPath());
				rd.forward(req, res);
			}
		}
	}
	
}
