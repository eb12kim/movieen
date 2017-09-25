package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.Action;
import net.member.action.ActionForward;

public class MemberFrontController extends HttpServlet implements Servlet {
	
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
				
		if(command.equals("/memberJoin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/member_joinForm.jsp");
		} else if(command.equals("/memberJoinAction.me")) {
			action = new MemberJoinAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberLogin.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/member_loginForm.jsp");
		} else if(command.equals("/memberLoginAction.me")) {
			action = new MemberLoginAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberLogout.me")) {
			action = new MemberLogoutAction();
		
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberView.me")) {
			action = new MemberViewAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberList.me")) {
			action = new MemberListAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberDelete.me")) {
			action = new MemberDeleteAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberUpdate.me")) {
			action = new MemberUpdateView();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberUpdateAction.me")) {
			action = new MemberUpdateAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberMyPageAction.me")) {
			action = new MemberMyPageAction();
			
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
