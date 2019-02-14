package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.FeedModifyService;
import vo.ActionForward;

public class feedModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 피드 수정
		
		HttpSession session = request.getSession();
		String feedNum = request.getParameter("feed_num");
		String feedId = (String) session.getAttribute("id");
		String feedPass = request.getParameter("password");
		String feedContent = request.getParameter("content");
		String search = (String) session.getAttribute("search");
		// 수정시 입력한 수정할 게시글내용과 비밀번호, 게시글번호를 받아온다.
		// 세션에있는 id와 search값을 받아옴
		ActionForward forward = null;
		FeedModifyService feedModifyService = new FeedModifyService();

		boolean modifyResult = false;
		modifyResult = feedModifyService.FeedModify(feedNum, feedId, feedPass, feedContent);
		//매개변수로 받은값들을 넘겨주고 메소드 실행
		
		if (modifyResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 오류')");
			out.println("history.back()</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("search.fe?search=" + search + "&sort=time");
			//수정이 성공하면 search값 넘겨주면서 List로 이동
		}
		return forward;
	}

}
