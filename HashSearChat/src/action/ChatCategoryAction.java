package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ChatCategoryService;
import vo.ActionForward;
import vo.Chat;

public class ChatCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 채팅 카테고리
		
		int chatCategory = Integer.parseInt(request.getParameter("category"));
		// chatList.jsp에서 누른 카테고리의 number값을 받아온다.
		ActionForward forward = new ActionForward();
		// forward객체 생성  
		ChatCategoryService ccs = new ChatCategoryService();
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		// 새로운 chatList를 생성한다.
		chatList = ccs.chatCategory(chatCategory);
		// 카테고리 number값에 해당하는 채팅방 리스트를 담아준다.
		request.setAttribute("chatList", chatList);
		// "chatList"로 새로운 리스트를 넘겨준다
		forward.setPath("chatList.jsp");
		return forward;
	}

}
