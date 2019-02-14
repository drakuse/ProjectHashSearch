package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import vo.Chat;
import vo.Message;

public class ChatDAO {
	
	// 계속 쓸것들
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private static ChatDAO chatDAO;
	private Connection conn;
	
	private ChatDAO() {

	}
	// 싱글톤 생성
	public static ChatDAO getInstance() {
		if (chatDAO == null) {
			chatDAO = new ChatDAO();
		}

		return chatDAO;
	}
	
	// 커넥션
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int chatWrite(Chat c) {
		// 채팅방 개설 메소드
		int result = 0;
		int num = 0;
		String sql = "INSERT INTO CHAT VALUES (?, ?, ?, SYSDATE, ?)";
		// 채팅방을 개설하는 쿼리문
		String sql1 = "SELECT MAX(CHAT_NUM) FROM CHAT";
		// 채팅방번호를 매겨주는 쿼리문
		try {
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;// 등록된 글이 있을때
			} else {
				num = 1;// 등록된 글이 없을때
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, c.getCHAT_ID());
			pstmt.setString(3, c.getCHAT_SUBJECT());
			pstmt.setInt(4, c.getCHAT_CATEGORY());
			result = pstmt.executeUpdate();
			// result로 성공, 실패여부 판단하기 위해 넣어줌

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
		return result;
	}

	public ArrayList<Chat> chatList(String search) {
		// 채팅방 목록 메소드
		String sql = "SELECT * FROM CHAT WHERE CHAT_SUBJECT like ? ORDER BY CHAT_NUM DESC";
		// 채팅방 목록을 가져오기위한 쿼리문
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		Chat chat = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			// search값을 받아와 포함되있다면 가져올 수 있게 조건 추가 ""이면 전체가져옴
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					chat = new Chat();
					chat.setCHAT_NUM(rs.getInt("CHAT_NUM"));
					chat.setCHAT_ID(rs.getString("CHAT_ID"));
					chat.setCHAT_SUBJECT(rs.getString("CHAT_SUBJECT"));
					chat.setCHAT_DATE(rs.getString("CHAT_DATE"));
					chat.setCHAT_CATEGORY(rs.getInt("CHAT_CATEGORY"));
					chatList.add(chat);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("오류" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return chatList;
	}

	public Chat chatDetail(Chat c) {
		// 채팅방정보 가져오기위한 메소드
		String sql = "SELECT * FROM CHAT WHERE CHAT_NUM = ?";
		//채팅방 번호로 해당 채팅방의 정보를 가져오는 쿼리문
		Chat chatDetail = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getCHAT_NUM());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				chatDetail = new Chat();
				chatDetail.setCHAT_NUM(rs.getInt("CHAT_NUM"));
				chatDetail.setCHAT_ID(rs.getString("CHAT_ID"));
				chatDetail.setCHAT_SUBJECT(rs.getString("CHAT_SUBJECT"));
				chatDetail.setCHAT_DATE(rs.getString("CHAT_DATE"));
				chatDetail.setCHAT_CATEGORY(rs.getInt("CHAT_CATEGORY"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return chatDetail;
	}

	public int chating(Message msg) {
		// 채팅 메시지를 저장하기 위한 메소드
		int result = 0;
		String sql = "INSERT INTO MESSAGE VALUES(?,?,?,SYSDATE)";
		// 채팅을 입력했을때 MESSAGE TABLE에 저장해주는 쿼리문
		// 채팅방번호와, 채팅을친 ID, MESSAGE내용이 저장된다.
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, msg.getCHAT_NUM());
			pstmt.setString(2, msg.getCHAT_ID());
			pstmt.setString(3, msg.getCHAT_MESSAGE());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Message> chatingPro(int num) {
		// 메시지 목록을 List에 담기위한 메소드
		String sql = "SELECT * FROM MESSAGE WHERE CHAT_NUM = ? ORDER BY CHAT_DATE ASC";
		// 저장된 모든 메시지를 시간순으로 정렬해 List에 담아주는 쿼리문, 출력을 하기 위함.
		ArrayList<Message> msgList = new ArrayList<Message>();
		Message msg = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				msg = new Message();
				msg.setCHAT_NUM(rs.getInt("CHAT_NUM"));
				msg.setCHAT_ID(rs.getString("CHAT_ID"));
				msg.setCHAT_MESSAGE(rs.getString("CHAT_MESSAGE"));
				msgList.add(msg);
			}

		} catch (Exception e) {
			System.out.println("오류" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return msgList;
	}

	public int chatDelete(String chatNum, String chatId, String chatPass) {
		// 채팅방을 삭제 메소드
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PASSWORD=?";
		// 채팅방을 삭제하기위해 권한이 있는 MEMBER인지 확인하는 쿼리문
		String sql1 = "DELETE CHAT WHERE CHAT_NUM=?";	
		// 채팅방을 삭제하기위한 쿼리문
		String sql2 = "DELETE MESSAGE WHERE CHAT_NUM = ?";
		// 해당 채팅방을 삭제하면서 해당 채팅방의 메시지도 모두 삭제하기위한 쿼리문
		int deleteResult = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chatId);
			pstmt.setString(2, chatPass);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, chatNum);

				deleteResult = pstmt.executeUpdate();
			}

			pstmt = conn.prepareStatement(sql2);
	        pstmt.setInt(1, Integer.parseInt(chatNum));
	        pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("삭제컬럼부분 오류" + e);
		} finally {
			close(pstmt);
			close(rs);
		}
		return deleteResult;
	}

	public int chatModify(String chatNum, String chatId, String chatPass, String chatSubject) {
		// 채팅방 수정 메소드
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PASSWORD=?";
		// 채팅방을 수정하기위해 권한이 있는 MEMBER인지 확인하는 쿼리문
		String sql1 = "UPDATE CHAT SET CHAT_SUBJECT=? WHERE CHAT_NUM=?";
		// 채팅방제목을 수정하기 위한 쿼리문
		int modifyRs = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chatId);
			pstmt.setString(2, chatPass);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println(chatSubject);
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1, chatSubject);
				pstmt.setString(2, chatNum);
				modifyRs = pstmt.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println("수정 컬럼부분 오류" + e);
		} finally {
			close(pstmt);
			close(rs);
		}
		return modifyRs;
	}

	public ArrayList<Chat> chatMember(String id) {
		// 특정 MEMBER의 채팅방만 List에 담기위한 메소드
		ArrayList<Chat> search = new ArrayList<Chat>();
		String sql = "SELECT * FROM CHAT WHERE CHAT_ID LIKE ? ORDER BY CHAT_NUM DESC";
		// 특정 MEMBER가 개설한 채팅방을 확인하기위해 조건 확인 후 List에 담아주는 메소드
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setCHAT_NUM(rs.getInt("CHAT_NUM"));
				chat.setCHAT_ID(rs.getString("CHAT_ID"));
				chat.setCHAT_SUBJECT(rs.getString("CHAT_SUBJECT"));
				chat.setCHAT_DATE(rs.getString("CHAT_DATE"));
				chat.setCHAT_CATEGORY(rs.getInt("CHAT_CATEGORY"));
				search.add(chat);
			}

		} catch (Exception e) {
			System.out.println("select 오류!!" + e);
		} finally {
			close(rs);
			close(pstmt);

		}
		return search;
	}

	public ArrayList<Chat> chatCategory(int chatCategory) {
		// 특정 카테고리의 채팅방만 List에 담기위한 메소드
		String sql = "SELECT * FROM CHAT WHERE CHAT_CATEGORY = ? ORDER BY CHAT_NUM DESC";
		// 특정 카테고리에 맞는 채팅방만 조건으로 검색해 List에 담아주기위한 쿼리문
		ArrayList<Chat> chatList = new ArrayList<Chat>();
		Chat chat = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatCategory);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					chat = new Chat();
					chat.setCHAT_NUM(rs.getInt("CHAT_NUM"));
					chat.setCHAT_ID(rs.getString("CHAT_ID"));
					chat.setCHAT_SUBJECT(rs.getString("CHAT_SUBJECT"));
					chat.setCHAT_DATE(rs.getString("CHAT_DATE"));
					chat.setCHAT_CATEGORY(rs.getInt("CHAT_CATEGORY"));
					chatList.add(chat);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("오류" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return chatList;
	}
}
