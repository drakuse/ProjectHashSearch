package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.RefuseService;
import vo.ActionForward;

public class RefuseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 게시글 신고 기능
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String feed_num = request.getParameter("feed_num");
		String member_id = (String) session.getAttribute("id");
		String feed_refuse=request.getParameter("feed_refuse");
		String search=(String)session.getAttribute("search");
		// 해당게시글정보와 기존 신고횟수를 받아온다
		
		RefuseService refuseService=new RefuseService();
		boolean countResult=refuseService.refuseCount(feed_num, member_id, feed_refuse);
		// 매개변수 전달하면서 메소드 실행
		
		if(countResult) {
			forward = new ActionForward();
			forward.setPath("search.fe?search="+search+"&sort=time");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이미 신고함')");
			out.println("history.back()</script>");
		}		

		return forward;
	}

}
