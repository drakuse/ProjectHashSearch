package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class MemberJoinService {
	public int memberJoin(Member m) {
		// memberDAO 싱글톤 객체 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		int result = memberDAO.memberJoin(m);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		// DB접속 해제
		close(con);

		return result;

	}

}
