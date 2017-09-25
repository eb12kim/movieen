package net.post.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.post.db.PostBean;
import net.post.db.PostDAO;

public class PostListByNationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		PostDAO postDAO = new PostDAO();
		
		// 네비게이션
		List<String> landList = new ArrayList<>();
		landList.add("아시아");
		landList.add("미주대륙");
		landList.add("유럽");
		landList.add("기타");
		req.setAttribute("landList", landList);
		
		List<Integer> landPostCount = new ArrayList<>();
		String[] As = {"대한민국", "대만", "베트남", "일본", "중국", "태국", "홍콩"};
		String[] Am = {"미국", "캐나다"};
		String[] Eu = {"독일", "스위스", "영국", "오스트리아", "이탈리아", "크로아티아", "프랑스"};
		landPostCount.add(postDAO.getPostListByLandCount(As));
		landPostCount.add(postDAO.getPostListByLandCount(Am));
		landPostCount.add(postDAO.getPostListByLandCount(Eu));
		landPostCount.add(postDAO.getPostListByLandCount_Etc());
		req.setAttribute("landPostCount", landPostCount);
		
		List<String[]> nationByLand = new ArrayList<>();
		nationByLand.add(As);
		nationByLand.add(Am);
		nationByLand.add(Eu);
		
		req.setAttribute("nationByLand", nationByLand);
		
		// 리스트 출력
		List<PostBean> postList = new ArrayList<>();
		
		int page = 1;
		int limit = 6;
		int listCount = 0;
		
		if(req.getParameter("page")!=null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		if(req.getParameter("land")!=null && req.getParameter("land")!="") {
			String land = req.getParameter("land");
			
			if(land.equals("아시아")) {
				listCount = postDAO.getPostListByLandCount(As);
				postList = postDAO.getPostListByLand(As, page, limit);
			} else if(land.equals("미주대륙")) {
				listCount = postDAO.getPostListByLandCount(Am);
				postList = postDAO.getPostListByLand(Am, page, limit);
			} else if(land.equals("유럽")) {
				listCount = postDAO.getPostListByLandCount(Eu);
				postList = postDAO.getPostListByLand(Eu, page, limit);
			} else {
				listCount = postDAO.getPostListByLandCount_Etc();
				postList = postDAO.getPostListByLand_Etc(page, limit);
			}
			
			req.setAttribute("land", land);
			req.setAttribute("nation", null);
		} else if(req.getParameter("nation")!=null && req.getParameter("nation")!="") {
			String nation = req.getParameter("nation");
			
			listCount = postDAO.getPostListByNationCount(nation);
			postList = postDAO.getPostListByNation(nation, page, limit);
			
			req.setAttribute("land", null);
			req.setAttribute("nation", nation);
		} else {
			listCount = postDAO.getListCount(1);
			postList = postDAO.getPostListLike(1, page, limit);
			
			req.setAttribute("land", null);
			req.setAttribute("nation", null);
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
		forward.setPath("./post/post_list_by_nation.jsp");
		
		return forward;
	}

}
