package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ChatWriteService;
import vo.ActionForward;
import vo.Chat;

public class ChatWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 채팅방 개설

		Chat c = new Chat();
		c.setCHAT_SUBJECT(request.getParameter("CHAT_SUBJECT"));
		c.setCHAT_ID(request.getParameter("CHAT_ID"));		
		c.setCHAT_CATEGORY(Integer.parseInt(request.getParameter("CHAT_CATEGORY")));
		// 채팅방 개설할때 입력한 제목, 카테고리와 세션에있는 ID를 받아온다.
		ChatWriteService cws = new ChatWriteService();
		int result = cws.chatWrite(c);
		// 메소드실행하고 int로 리턴받음
		ActionForward forward = null;
		if (result > 0) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("chatListAction.ch?search=");
			// 성공했으면 List로 이동
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('채팅방개설 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
