package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.ChatDAO;
import vo.Chat;

public class ChatWriteService {

	public int chatWrite(Chat c) {

		//LoginDAO 클래스에 대한 싱글톤 객체생성
				ChatDAO chatDAO = ChatDAO.getInstance();
				// jdbcUtil 클래스에 정의한 메소드를 통해 DB접속
				Connection conn = getConnection();
				chatDAO.setConnection(conn);
				int result = chatDAO.chatWrite(c);
				
				if(result > 0) {
					commit(conn);			
				} else {
					rollback(conn);
				}
				// DB접속 해제
				close(conn);

				return result;
		
	}

}
