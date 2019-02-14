package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberJoinService;
import vo.ActionForward;
import vo.Member;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 회원가입
		
		Member m = new Member();
		m.setMEMBER_ID(request.getParameter("MEMBER_ID"));
		m.setMEMBER_PASSWORD(request.getParameter("MEMBER_PASSWORD"));
		m.setMEMBER_NAME(request.getParameter("MEMBER_NAME"));
		// 회원가입 Form에서 입력한 ID, PASSWORD, NAME을 받아온다
		// 받아온 값들을 Member타입 변수 m에 저장
		MemberJoinService mjs = new MemberJoinService();
		int result = mjs.memberJoin(m);
		// Member변수 m을 넘겨주면서 회원가입 메소드 실행
		ActionForward forward = null;

		if (result == 1) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.jsp");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패')");
			out.println("history.back()");
			out.println("</script>");
		}

		return forward;
	}

}
