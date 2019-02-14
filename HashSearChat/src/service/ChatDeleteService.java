package service;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ChatDAO;

public class ChatDeleteService {

	public boolean chatDelete(String chatNum, String chatId, String chatPass) {
		// chatDAO 싱글톤 객체 생성
		ChatDAO chatDAO = ChatDAO.getInstance();
		Connection con = getConnection();
		chatDAO.setConnection(con);

		boolean chatDeleteResult = false;

		int result = chatDAO.chatDelete(chatNum, chatId, chatPass);
		System.out.println(result);
		if (result > 0) {
			chatDeleteResult = true;
			commit(con);
		} else {
			chatDeleteResult = false;
			rollback(con);
		}
		return chatDeleteResult;
	}

}
