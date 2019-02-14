package service;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.ChatDAO;
import vo.Chat;

public class ChatDetailService {

	public Chat chatDetail(Chat c) {
		// chatDAO 싱글톤 객체 생성
		Connection conn = getConnection();
		ChatDAO chatDAO = ChatDAO.getInstance();
		chatDAO.setConnection(conn);
		
		Chat chatDetail = chatDAO.chatDetail(c);
		close(conn);

		return chatDetail;
	}

}
