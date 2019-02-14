package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FeedMemberService;
import vo.ActionForward;
import vo.Feed;

public class feedMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 특정 id 피드 목록
		
		String id=request.getParameter("member_id");
		// 내가 만든 게시글만 보기위해 Form에서 세션에있는 id를 "member_id"로 넘겨주고 받아온다.
		ActionForward forward = null;
		ArrayList<Feed> feedList = new ArrayList<Feed>();

		FeedMemberService feedMemberService = new FeedMemberService();

		feedList = feedMemberService.feedMember(id);
		//id를 매개변수로 List를 가져온다
		request.setAttribute("feedList", feedList);
		forward = new ActionForward();
		forward.setPath("index.jsp");
		// List넘겨주고 index.jsp로 이동
		return forward;
	}

}
