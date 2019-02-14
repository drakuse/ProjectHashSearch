package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ChatDAO;
import vo.Chat;

public class ChatListService {

	public ArrayList<Chat> chatList(String search) {
		// chatDAO 싱글톤 객체 생성
		Connection con = getConnection();
		ChatDAO chatDAO = ChatDAO.getInstance();
		chatDAO.setConnection(con);
		
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		chatList = chatDAO.chatList(search);
		close(con);
		
		return chatList;
	}

}
