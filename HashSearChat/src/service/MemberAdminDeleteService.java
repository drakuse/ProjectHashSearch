package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberAdminDeleteService {
	public boolean memberDelete(String id) {
		// memberDAO 싱글톤 객체 생성
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int deleteResult = memberDAO.memberDelete(id);
		boolean result = false;
		
		if(deleteResult > 0) {
			result = true;
			commit(con);
		} else {
			result = false;
			rollback(con);
		}
		return result;
	}
}
