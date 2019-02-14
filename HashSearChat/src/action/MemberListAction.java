package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberListService;
import vo.ActionForward;
import vo.Member;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 관리자모드		
		ActionForward forward = null;
		String search=request.getParameter("search");
		forward = new ActionForward();

		MemberListService memberListService = new MemberListService();
		ArrayList<Member> memberList = memberListService.memberList(search);
		request.setAttribute("memberList", memberList);
		forward.setPath("member/memberList.jsp");
		return forward;
	}

}
