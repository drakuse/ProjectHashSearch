package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.FeedDeleteService;
import vo.ActionForward;

public class feedDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 피드 삭제
		
		HttpSession session = request.getSession();

		String feedNum = request.getParameter("feed_num");
		String feedId = (String) session.getAttribute("id");
		String feedPass = request.getParameter("password");
		String search=(String)session.getAttribute("search");
		// 게시글을 삭제할 때 게시글번호, 입력한 패스워드, 세션에담긴 id와, search값을 받아옴 
		ActionForward forward = null;
		FeedDeleteService feedDeleteService = new FeedDeleteService();

		boolean deleteResult = feedDeleteService.FeedDelete(feedNum, feedId, feedPass);
		// id와 password로 확인하고 게시글 number로 비교하기위해 매개변수 전달
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (deleteResult == false) {
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다.')");
			out.println("hystory.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("search.fe?search="+search+"&sort=time");
			//리스트로 이동
		}
		return forward;
	}

}
