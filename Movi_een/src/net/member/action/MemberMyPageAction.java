package net.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;
import net.post.db.PostBean;
import net.post.db.PostDAO;
import net.post.db.ReplyBean;

public class MemberMyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		
		PostDAO postDAO = new PostDAO();
		MemberDAO memberDAO = new MemberDAO();
		MemberBean member = new MemberBean();
		
		String loginId = (String)session.getAttribute("LoginId");
		
		if(loginId == null) {
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		member = memberDAO.getDetailMember(loginId);
		
		if(member == null) {
			System.out.println("회원 정보 읽기 실패");
			return null;
		}
		
		req.setAttribute("member", member);
		
		
		// 작성글 댓글단글 좋아요한글
		// 일단은 10개씩만
		
		int myPostListCount = postDAO.getMyListCount(loginId);
		req.setAttribute("myPostListCount", myPostListCount);
		
		List<PostBean> myPostList = postDAO.getMyList(loginId, 1, 10);
		req.setAttribute("myPostList", myPostList);
		
		int myReplyListCount = postDAO.getMyReplyListCount(loginId);
		req.setAttribute("myReplyListCount", myReplyListCount);
		
		List<ReplyBean> myReplyList = postDAO.getMyReplyList(loginId, 1, 10);
		req.setAttribute("myReplyList", myReplyList);
		
		int myLikeListCount = postDAO.getMyLikeListCount(loginId);
		req.setAttribute("myLikeListCount", myLikeListCount);
		
		List<PostBean> myLikeList = postDAO.getMyLikeList(loginId, 1, 10);
		req.setAttribute("myLikeList", myLikeList);
		
		int myGetLikeCount = postDAO.getGetLikeCount(loginId);
		req.setAttribute("myGetLikeCount", myGetLikeCount);
		
		
		// 영화, 여행 선호
		// 메인게시판 모양으로 6개씩
		
		int movieLikeListCount = postDAO.getMovieLikeListCount(member.getMEMBER_MOVIE_LIKE());
		req.setAttribute("movieLikeListCount", movieLikeListCount);
		
		List<PostBean> movieLikeList = postDAO.getMovieLikeList(member.getMEMBER_MOVIE_LIKE(),1,6);
		req.setAttribute("movieLikeList", movieLikeList);

		int tripLikeListCount = postDAO.getTripLikeListCount(member.getMEMBER_TRIP_LIKE());
		req.setAttribute("tripLikeListCount", tripLikeListCount);
		
		List<PostBean> tripLikeList = postDAO.getTripLikeList(member.getMEMBER_TRIP_LIKE(),1,6);
		req.setAttribute("tripLikeList", tripLikeList);
		
		forward.setRedirect(false);
		forward.setPath("./member/member_myPage.jsp");
		
		return forward;
	}

}
