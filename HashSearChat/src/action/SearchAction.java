package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.SearchService;
import vo.ActionForward;
import vo.Feed;

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 검색기능
		
		String text = request.getParameter("search");
		String sort = request.getParameter("sort");
		// 정렬기준과 SEARCH 입력값을 받아옴
		HttpSession session = request.getSession();
		session.setAttribute("search", text);
		// "search"에 담아준다
		ActionForward forward = null;
		ArrayList<Feed> feedList = new ArrayList<Feed>();
		SearchService searchService = new SearchService();
		feedList = searchService.search(text, sort);
		request.setAttribute("feedList", feedList);
		// 리스트 받아와서 넘겨줌
		forward = new ActionForward();
		forward.setPath("index.jsp");

		return forward;
	}

}
