package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ChatDeleteService;
import vo.ActionForward;

public class ChatDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 채팅방 삭제
		
		HttpSession session = request.getSession();

		String chatNum = request.getParameter("chat_num");
		String chatId = (String) session.getAttribute("id");
		String chatPass = request.getParameter("password");
		// 삭제시 넘겨주는 값들

		ActionForward forward = null;
		ChatDeleteService cds = new ChatDeleteService();
		boolean deleteResult = cds.chatDelete(chatNum, chatId, chatPass);
		// id와 password는 확인을 위해 넘겨주고 삭제 할 채팅방 number를 매개변수로 넘겨준다.
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (deleteResult == false) {
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다.')");
			out.println("hystory.back()");
			out.println("</script>");
			// id와 password가 다를경우 alert를 띄운다
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("chatListAction.ch?search=");
			// 삭제하고 새로운 chatList로 이동
		}
		return forward;
	}

}
