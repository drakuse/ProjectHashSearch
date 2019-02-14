package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Member;

public class MemberDAO {
	private static MemberDAO memberDAO;
	private Connection con;

	private MemberDAO() {

	}

	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int memberJoin(Member m) {
		// 회원가입 메소드
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "INSERT INTO MEMBER VALUES (?, ?, ?, ?)";
		// 입력한 회원정보를 MEMBER TABLE에 추가하기 위한 쿼리문
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getMEMBER_ID());
			pstmt.setString(2, m.getMEMBER_PASSWORD());
			pstmt.setString(3, m.getMEMBER_NAME());
			pstmt.setInt(4, 0);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int memberLogin(String id, String pass) {
		// 로그인 메소드
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT MEMBER_PASSWORD FROM MEMBER WHERE MEMBER_ID = ?";
		// 입력한 ID, PASSWORD가 존재하고, 일치하는지 확인하기 위한 쿼리문
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(pass)) {
					// ID와 PASSWORD가 모두 일치하면 1을 리턴
					result = 1;
				} else {
					// PASSWORD가 일치하지 않으면 0을 리턴
					result = 0;
				}

			} else {
				// 맞는 ID가 없어 IF문 자체를 건너뛴다면 -1을 리턴
				result = -1;
			}
		} catch (Exception e) {
			System.out.println("DAO로그인실패");
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public Member memberInfo(String member_id, String member_pass) {
		// MEMBER정보를 받아오는 메소드
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member=new Member();
		
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PASSWORD=?";
		// ID와 PASSWORD를 검사해서 정보를 가져오기위한 쿼리문
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_pass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setMEMBER_ID(rs.getString(1));
				member.setMEMBER_PASSWORD(rs.getString(2));
				member.setMEMBER_NAME(rs.getString(3));
				member.setMEMBER_REFUSE(rs.getInt(4));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
				close(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	public int update(String member_id, String new_pass) {
		// 회원정보수정 메소드
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "UPDATE MEMBER SET MEMBER_PASSWORD=? WHERE MEMBER_ID=?";
		// ID와 PASSWORD를 검사해서 해당 회원의 PASSWORD를 수정하기 위한 쿼리문
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, new_pass);
			pstmt.setString(2, member_id);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int delete(String member_id) {
		// 회원탈퇴 메소드
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "DELETE MEMBER WHERE MEMBER_ID=?";
		// 해당 ID를 MEMBER TABLE에서 삭제하기위한 쿼리문
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<Member> memberList(String search) {
		// 모든 회원의 정보를 가져오기위한 메소드
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Member member = null;
		ArrayList<Member> memberList = new ArrayList<Member>();
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID LIKE ? ORDER BY MEMBER_REFUSE DESC";
		// 신고순으로 모든 회원의 정보를 가져오기위한 쿼리문
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new Member();
				member.setMEMBER_ID(rs.getString("MEMBER_ID"));
				member.setMEMBER_PASSWORD(rs.getString("MEMBER_PASSWORD"));
				member.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
				member.setMEMBER_REFUSE(rs.getInt("MEMBER_REFUSE"));
				
				memberList.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return memberList;
	}
	public int memberDelete(String id) {
		// 회원탈퇴 메소드
		PreparedStatement pstmt = null;
		int deleteResult = 0;
		
		String sql = "DELETE MEMBER WHERE MEMBER_ID=?";
		// 해당 ID를 MEMBER TABLE에서 삭제하기위한 쿼리문		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			deleteResult = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteResult;
	}
}
