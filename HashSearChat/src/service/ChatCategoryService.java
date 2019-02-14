package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ChatDAO;
import vo.Chat;

public class ChatCategoryService {

	public ArrayList<Chat> chatCategory(int chatCategory) {
		// chatDAO 싱글톤 객체 생성
		Connection con = getConnection();
		ChatDAO chatDAO = ChatDAO.getInstance();
		chatDAO.setConnection(con);
		
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		chatList = chatDAO.chatCategory(chatCategory);
		close(con);
		// DB접속 해제		
		return chatList;
	}

}
