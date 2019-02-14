package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberManagerService {

	public void update(String member_id, String new_pass) {
		// memberDAO 싱글톤 객체 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		int result = memberDAO.update(member_id, new_pass);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		// DB접속 해제
		close(con);		
	}

	public void delete(String member_id) {
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		int result = memberDAO.delete(member_id);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		// DB접속 해제
		close(con);				
	}

}
