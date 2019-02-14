package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.FeedWriteService;
import vo.ActionForward;
import vo.Feed;

public class feedWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 피드작성
		
		HttpSession session = request.getSession();
		
		int category=Integer.parseInt(request.getParameter("category"));
		String id = (String) session.getAttribute("id");
		String search=(String)session.getAttribute("search");
		Feed feed = new Feed();
		feed.setFeed_id(id);
		feed.setFeed_content(request.getParameter("feed_content"));
		feed.setFeed_category(category);
		// 게시글 작성 할 때 입력한 내용, 카테고리와 세션에있는 ID, search값을 받아온다.
		// Feed객체 생성해서 담아준다
		boolean writeResult = false;

		FeedWriteService feedWriteService = new FeedWriteService();
		writeResult = feedWriteService.feedWrite(feed);
		// 생성한 Feed객체 매개변수로 넘겨줌
		ActionForward forward = null;
		if (writeResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back()</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("search.fe?search="+search+"&sort=time");
			//리스트로 이동
		}

		return forward;
	}

}