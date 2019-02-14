package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberLoginService {

	public int memberLogin(String id, String pass) {
		// memberDAO 싱글톤 객체 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		int result = memberDAO.memberLogin(id, pass);
		close(con);

		return result;
	}

}
