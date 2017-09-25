package net.movie.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.movie.action.Action;
import net.movie.action.ActionForward;

public class MovieFrontController extends HttpServlet implements Servlet {
	
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
		
		if(command.equals("/movieList.mo")) {
			action = new MovieListAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/movieAdd.mo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./movie/movie_addForm.jsp");
		} else if(command.equals("/movieAddAction.mo")) {
			action = new MovieAddAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/movieInfo.mo")) {
			action = new MovieViewAction();
			
			try {
				forward = action.execute(req, res);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/movieDelete.mo")) {
			action = new MovieDeleteAction();
		
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
