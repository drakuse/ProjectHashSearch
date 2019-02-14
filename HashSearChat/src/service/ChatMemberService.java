package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ChatDAO;
import vo.Chat;

public class ChatMemberService {

	public ArrayList<Chat> chatMember(String id) {
		// chatDAO 싱글톤 객체 생성
		ChatDAO chatDAO = ChatDAO.getInstance();
		Connection con = getConnection();
		chatDAO.setConnection(con);

		ArrayList<Chat> chatList = new ArrayList<Chat>();

		chatList=chatDAO.chatMember(id);
		close(con);

		return chatList;
	}

}
