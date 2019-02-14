package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LikeService;
import vo.ActionForward;

public class LikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 좋아요 기능
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String feed_num = request.getParameter("feed_num");
		String member_id = (String) session.getAttribute("id");
		String feed_like=request.getParameter("feed_like");
		String search=(String)session.getAttribute("search");
		// 좋아요를 클릭한 게시글의 정보를 받아온다
		LikeService likeService=new LikeService();
		boolean countResult=likeService.likeCount(feed_num, member_id, feed_like);
		// 메소드 실행
		if(countResult) {
			forward = new ActionForward();
			forward.setPath("search.fe?search="+search+"&sort=time");
			// 최신순 정렬으로 List로 이동
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이미 추천함')");
			out.println("history.back()</script>");
			// 해당아이디가 이미추천했으면  alert출력
		}		

		return forward;
	}

}
