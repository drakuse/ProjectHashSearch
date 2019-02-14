package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberAdminDeleteService;
import vo.ActionForward;

public class MemberAdminDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 신고횟수 누적 ID 삭제
		
		String id = request.getParameter("MEMBER_ID");
		// 삭제할 id를 받아온다
		
		ActionForward forward = null;
		MemberAdminDeleteService memberAdminDeleteService = new MemberAdminDeleteService();
		
		boolean result = false;
		result = memberAdminDeleteService.memberDelete(id);
		// 회원 삭제 메소드 실행
		if(result == true) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("memberListAction.me");
		}
		return forward;
	}
}
