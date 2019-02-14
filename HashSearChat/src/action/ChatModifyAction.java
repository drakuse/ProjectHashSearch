package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ChatModifyService;
import vo.ActionForward;

public class ChatModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 채팅방 제목 수정
		
		HttpSession session = request.getSession();
		String chatNum = request.getParameter("chat_num");
		String chatId = (String) session.getAttribute("id");
		String chatPass = request.getParameter("password");
		String chatSubject = request.getParameter("chat_subject");
		String search=(String)session.getAttribute("search");
		// 수정시 입력한 수정할 채팅방제목과 비밀번호, 방번호를 받아온다.
		// 세션에있는 id와 search값을 받아옴
		ActionForward forward = null;
		ChatModifyService csm = new ChatModifyService();

		boolean modifyResult = false;
		modifyResult = csm.chatModify(chatNum, chatId, chatPass, chatSubject);
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
			forward.setPath("chatListAction.ch?search="+search);
			//수정이 성공하면 search값 넘겨주면서 List로 이동
		}
		return forward;
	}

}
