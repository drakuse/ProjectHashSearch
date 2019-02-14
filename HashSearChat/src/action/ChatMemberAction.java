package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ChatMemberService;
import vo.ActionForward;
import vo.Chat;

public class ChatMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 특정ID 채팅방목록
		
		String id = request.getParameter("member_id");
		// 내가 만든 채팅방만 보기위해 Form에서 세션에있는 id를 "member_id"로 넘겨주고 받아온다.
		ActionForward forward = null;
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		ChatMemberService cms = new ChatMemberService();
		chatList = cms.chatMember(id);
		//id를 매개변수로 List를 가져온다
		request.setAttribute("chatList", chatList);
		forward = new ActionForward();
		forward.setPath("chatList.jsp");
		// List넘겨주고 chatList.jsp로 이동
		return forward;
	}

}
