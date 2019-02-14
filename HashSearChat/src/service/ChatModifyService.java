package service;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ChatDAO;

public class ChatModifyService {

	public boolean chatModify(String chatNum, String chatId, String chatPass, String chatSubject) {
		// chatDAO 싱글톤 객체 생성
		ChatDAO chatDAO = ChatDAO.getInstance();
		Connection con = getConnection();
		chatDAO.setConnection(con);

		boolean chatModifyResult = false;

		int result = chatDAO.chatModify(chatNum, chatId, chatPass, chatSubject);
		System.out.println(result);
		if (result > 0) {
			chatModifyResult = true;
			commit(con);
		} else {
			chatModifyResult = false;
			rollback(con);
		}
		return chatModifyResult;
	}

}
