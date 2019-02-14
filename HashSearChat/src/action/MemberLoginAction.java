package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberLoginService;
import vo.ActionForward;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인
		
		String id = request.getParameter("MEMBER_ID");
		String pass = request.getParameter("MEMBER_PASSWORD");
		// 로그인Form에서 입력한 ID, PASSWORD값 받아옴
		MemberLoginService mls = new MemberLoginService();
		int result = mls.memberLogin(id, pass);
		// ID, PASSWORD 매개변수로 메소드 실행
		ActionForward forward = null;
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.jsp");
			// 메인으로이동
		} else if (result == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다')");
			out.println("history.back()</script>");
		} else if (result == -1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('존재하지 않는 아이디 입니다.')");
			out.println("history.back()</script>");
			// 실패경우 다시 로그인화면으로
		}

		return forward;
	}

}
