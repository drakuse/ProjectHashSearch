package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ChatDetailService;
import service.ChatListService;
import service.ChatingService;
import vo.ActionForward;
import vo.Chat;
import vo.Message;

public class ChatInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 채팅방 정보

		int chatNum = Integer.parseInt(request.getParameter("CHAT_NUM"));
		String search=request.getParameter("search");
		String message=request.getParameter("CHAT_MESSAGE");
		String chatId=request.getParameter("CHAT_ID");
		// 채팅방입장을 눌렀을때 받아오는 값들
		
		ActionForward forward = null;
		
		ChatingService cs = new ChatingService();
		
		if(message!=null) {
		//입장시에 보내는 메시지가 없기 때문에 값이 비게되어서 보내는 메시지가 있을때만 실행하도록 if문 추가
			Message msg = new Message();
			msg.setCHAT_NUM(chatNum);
			msg.setCHAT_ID(chatId);
			msg.setCHAT_MESSAGE(message);
			// 메시지를 보낼시 ID, 채팅방NUMBER, 채팅내용을 저장
			cs.chating(msg);
		}		
		
		Chat chatDetail = new Chat();
		chatDetail.setCHAT_NUM(chatNum);
		
		ChatDetailService cds = new ChatDetailService();
		chatDetail = cds.chatDetail(chatDetail);
		// 채팅방 정보를 담음
		
		ArrayList<Message> msgList = new ArrayList<Message>();
		msgList = cs.chatingPro(chatNum);
		// 해당 채팅방의 원래있던 메시지를 담음
		
		ChatListService cls = new ChatListService();
		ArrayList<Chat> chatList = cls.chatList(search);
		//채팅방 왼쪽에 채팅방 List를 띄워야 하기 떄문에 채팅방 List를 담음
		
		forward = new ActionForward();
		request.setAttribute("chatList", chatList);
		request.setAttribute("chatDetail", chatDetail);
		request.setAttribute("msgList", msgList);
		forward.setPath("chat/chatForm.jsp");
		// 담은것들 모두 넘겨주고 chatForm.jsp로 이동

		return forward;
	}

}
