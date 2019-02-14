package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberInfoService;
import vo.ActionForward;
import vo.Member;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 회원정보
		
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("id");
		String member_pass = request.getParameter("password");
		// 세션 id와 입력한 password를 받아옴
		Member member = new Member();
		ActionForward forward = null;
		MemberInfoService memberInfoService = new MemberInfoService();
		member = memberInfoService.memberInfo(member_id, member_pass);
		// id와 password를 매개변수로 메소드실행
		if (member.getMEMBER_ID() != null) {
			request.setAttribute("memberInfo", member); 
			forward = new ActionForward();
			forward.setPath("member/memberInfo.jsp");			
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.')");
			out.println("history.back()</script>");
		}
		return forward;
	}

}
