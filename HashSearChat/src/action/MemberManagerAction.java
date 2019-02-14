package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberLoginService;
import service.MemberManagerService;
import vo.ActionForward;

public class MemberManagerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 회원정보수정
		
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("id");
		String select=request.getParameter("select");
		String pass=request.getParameter("password");
		// 세션의 ID와 입력한 PASSWORD받아오고 SELECT(정보수정/회원삭제)값 받아온다
		MemberLoginService mls = new MemberLoginService();
		int result = mls.memberLogin(member_id, pass);
		// 매개변수로 메소드실행
		ActionForward forward = null;
		MemberManagerService memberManagerService=new MemberManagerService();
		
		if (result == 1) {
			if(select.equals("정보수정")) {		
				String new_pass=request.getParameter("new_password");
				memberManagerService.update(member_id, new_pass);
				// 정보수정이 SELECT되었을때 새로운비밀번호를 받아와서 회원정보 업데이트 메소드를 실해한다
			}else {
				memberManagerService.delete(member_id);
				// 회원탈퇴가 SELECT되었을때 해당 ID삭제
				session.invalidate();
			}			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.jsp");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다')");
			out.println("history.back()</script>");
		}				
		return forward;
	}

}
