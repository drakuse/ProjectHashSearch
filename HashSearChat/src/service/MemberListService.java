package service;

import java.sql.Connection;
import java.util.*;
import dao.MemberDAO;
import vo.Member;

import static db.JdbcUtil.*;

public class MemberListService {
	public ArrayList<Member> memberList(String search) {
		// memberDAO 싱글톤 객체 생성		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		ArrayList<Member> memberLsit = memberDAO.memberList(search);
		
		return memberLsit;
	}
}
