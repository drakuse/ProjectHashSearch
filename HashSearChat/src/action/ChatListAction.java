package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ChatListService;
import vo.ActionForward;
import vo.Chat;

public class ChatListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 채팅방 목록
		
		String search=request.getParameter("search");
		// chatList에서 search값이 있을때 받아옴
		ActionForward forward = new ActionForward();
		ChatListService cls = new ChatListService();
		
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		chatList = cls.chatList(search);
		// Chat타입의 List에 search값을 넘겨줘서 리스트를 받아온다(search 기본값은 "")
		
		request.setAttribute("chatList", chatList);
		forward.setPath("chatList.jsp");
		// 받아온 List를 넘겨주고 chatList.jsp로 이동
		return forward;
	}

}
