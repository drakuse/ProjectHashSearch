package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ChatDAO;
import vo.Message;

public class ChatingService {

	public int chating(Message msg) {
		// chatDAO 싱글톤 객체 생성
		Connection con = getConnection();
		ChatDAO chatDAO = ChatDAO.getInstance();
		chatDAO.setConnection(con);

		int result = chatDAO.chating(msg);
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return result;
	}

	public ArrayList<Message> chatingPro(int num) {
		Connection con = getConnection();
		ChatDAO chatDAO = ChatDAO.getInstance();
		chatDAO.setConnection(con);

		ArrayList<Message> msgList = chatDAO.chatingPro(num);
		
		return msgList;
	}

}
