package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class MemberInfoService {

	public Member memberInfo(String member_id, String member_pass) {
		// memberDAO 싱글톤 객체 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);

		Member member = new Member();

		member=memberDAO.memberInfo(member_id, member_pass);
		close(con);

		return member;
	}

}
