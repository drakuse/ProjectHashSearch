package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FeedCategoryService;
import vo.ActionForward;
import vo.Feed;

public class FeedCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 피드 카테고리
		
		int category=Integer.parseInt(request.getParameter("category"));
		int sort=Integer.parseInt(request.getParameter("sorts"));
		// 게시글 목록에서 누른 카테고리값과 정렬값을 받아옴

		ActionForward forward = null;
		ArrayList<Feed> feedList = new ArrayList<Feed>();

		FeedCategoryService feedCategoryService = new FeedCategoryService();
		feedList = feedCategoryService.feedCategory(category, sort);
		//매개변수로 넘겨주고 메소드 실행
		
		request.setAttribute("feedList", feedList);
		forward = new ActionForward();
		forward.setPath("index.jsp");
		//뽑아온 List를 넘겨주고 게시글목록으로 이동.
		return forward;
		
	}

}
