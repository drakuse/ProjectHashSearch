package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Feed;

public class FeedDAO {
	private static FeedDAO feedDAO;
	private Connection con;

	private FeedDAO() {

	}

	public static FeedDAO getInstance() {
		if (feedDAO == null) {
			feedDAO = new FeedDAO();
		}
		return feedDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	@SuppressWarnings("resource")
	public int feedWrite(Feed feed) {
		// 피드생성 메소드
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Feed feedWrite = feed;
		String sql1 = "select max(feed_num) as max from feed";
		// FEED_NUM을 할당해주는 쿼리문
		String sql2 = "insert into feed values(?,?,?,SYSDATE,?,?,?)";
		// 피드를 생성하기 위한 쿼리문
		int feed_num = 0;
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				feed_num = rs.getInt("max") + 1;
			} else {
				feed_num = 1;
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, feed_num);
			pstmt.setString(2, feedWrite.getFeed_id());
			pstmt.setString(3, feedWrite.getFeed_content());
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, feedWrite.getFeed_category());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("update 오류!!" + e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Feed> search(String text, String sort) {
		// 검색값에 따라 List를 가져오기위한 메소드
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String txt = "%" + text + "%";
		System.out.println(txt);
		ArrayList<Feed> search = new ArrayList<Feed>();
		String sql = null;
		if (sort.equals("time")) {
			sql = "select * from feed where feed_content like ? order by feed_num desc";
			// 정렬값이 시간순이라면, 피드List를 받아오고 시간순으로 정렬해주는 쿼리문
		} else {
			sql = "select * from feed where feed_content like ? order by feed_like desc, feed_num desc";
			// 그렇지 않다면(추천순이라면) 피드 List를 받아오고 추천순으로 정렬해주는 쿼리문
		}

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, txt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Feed feed = new Feed();
				feed.setFeed_num(rs.getInt(1));
				feed.setFeed_id(rs.getString(2));
				feed.setFeed_content(rs.getString(3));
				feed.setFeed_date(rs.getDate(4));
				feed.setFeed_like(rs.getInt(5));
				feed.setFeed_refuse(rs.getInt(6));

				search.add(feed);
			}

		} catch (Exception e) {
			System.out.println("select 오류!!" + e);
		} finally {
			close(rs);
			close(pstmt);

		}
		return search;
	}

	@SuppressWarnings("resource")
	public int likeCount(String feed_num, String member_id, String feed_like) {
		// 좋아요 기능 메소드
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		int count = 0;
		count = Integer.parseInt(feed_like) + 1;
		// 좋아요가 눌려 메소드가 실행되면, 기존의 좋아요값보다 1증가시켜줌
		String sql1 = "select * from feed_count where count_id=? and count_num=? and count_like='1'";
		// 좋아요를 누르면 해당 게시글을 누른 ID의 count_like을 1로 설정해서 중복으로 누를수 없게 하는 쿼리문
		String sql2 = "insert into feed_count values(?,?,?,?)";
		// 좋아요를 누른 ID, 게시글NUMBER 등 정보 저장하기위한 쿼리문
		String sql3 = "update feed set feed_like=? where feed_num=?";
		// 해당 게시글의 좋아요수를 UPDATE하는 쿼리문
		try {
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, member_id);
			pstmt.setString(2, feed_num);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, member_id);
				pstmt.setString(2, feed_num);
				pstmt.setInt(3, 1);
				pstmt.setInt(4, 0);
				result = pstmt.executeUpdate();

				pstmt = con.prepareStatement(sql3);
				pstmt.setInt(1, count);
				pstmt.setString(2, feed_num);
				result = pstmt.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println("update 오류!!" + e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	@SuppressWarnings("resource")
	public int feedDelete(String feedNum, String feedId, String feedPass) {
		// 피드 삭제 메소드
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PASSWORD=?";
		// 피드를 삭제하기위해 권한이 있는 MEMBER인지 확인하는 쿼리문
		String sql1 = "DELETE FEED WHERE FEED_NUM=?";
		// 피드삭제쿼리문
		int deleteResult = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, feedId);
			pstmt.setString(2, feedPass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString("member_id"));
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, feedNum);

				deleteResult = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("삭제컬럼부분 오류" + e);
		} finally {
			close(pstmt);
			close(rs);
		}
		return deleteResult;
	}

	@SuppressWarnings("resource")
	public int feedModify(String feedNum, String feedId, String feedPass, String feedContent) {
		// 피드 수정 메소드
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PASSWORD=?";
		// 피드를 수정하기위해 권한이 있는 MEMBER인지 확인하는 쿼리문
		String sql1 = "UPDATE FEED SET FEED_CONTENT=? WHERE FEED_NUM=?";
		// 피드수정쿼리문
		int modifyRs = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, feedId);
			pstmt.setString(2, feedPass);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, feedContent);
				pstmt.setString(2, feedNum);
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

	@SuppressWarnings("resource")
	public int refuseCount(String feed_num, String member_id, String feed_refuse) {
		// 신고기능 메소드
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		int count = 0;

		count = Integer.parseInt(feed_refuse) + 1;
		// 신고가 눌려 메소드가 실행되면, 기존의 신고값보다 1증가시켜줌
		String id = null;

		String sql1 = "select * from feed_count where count_id=? and count_num=? and count_refuse='1'";
		// 신고를 누르면 해당 게시글을 누른 ID의 count_refuse을 1로 설정해서 중복으로 누를수 없게 하는 쿼리문
		String sql2 = "insert into feed_count values(?,?,?,?)";
		// 신고를 누른 ID, 게시글NUMBER 등 정보 저장하기위한 쿼리문
		String sql3 = "update feed set feed_refuse=? where feed_num=?";
		// 해당 게시글의 좋아요수를 UPDATE하는 쿼리문
		String sql4 = "select feed_id from feed where feed_num=?";
		// 신고당한 피드의 작성자를 가져오기위한 쿼리문
		String sql5 = "select * from member where member_id=?";
		// 신고당한 피드의 작성자의 정보를 가져오기위한 쿼리문
		String sql6 = "update member set member_refuse=? where member_id=?";
		// 신고당한 피드의 작성자의 신고횟수를 증가시켜주기위한 쿼리문
		try {
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, member_id);
			pstmt.setString(2, feed_num);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, member_id);
				pstmt.setString(2, feed_num);
				pstmt.setInt(3, 0);
				pstmt.setInt(4, 1);
				result = pstmt.executeUpdate();
				System.out.println("feed_count table");

				pstmt = con.prepareStatement(sql3);
				pstmt.setInt(1, count);
				pstmt.setString(2, feed_num);
				result = pstmt.executeUpdate();
				System.out.println("feed table");
				
				pstmt = con.prepareStatement(sql4);
				pstmt.setString(1, feed_num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					id = rs.getString("feed_id");
					System.out.println("신고당한넘"+id);				
					
					pstmt = con.prepareStatement(sql5);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
					if (rs.next()) {						
						int member_refuse=rs.getInt("member_refuse");
						System.out.println(rs.getString("member_id")+" 멤버신고수"+member_refuse);
						
						pstmt = con.prepareStatement(sql6);
						pstmt.setInt(1, member_refuse+1);
						pstmt.setString(2, id);
						result = pstmt.executeUpdate();
						System.out.println("member table");
						System.out.println(result);
					}
				}				
			}

		} catch (Exception e) {
			System.out.println("update 오류!!" + e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	

	public ArrayList<Feed> feedMember(String id) {
		// 특정 작성자의 피드List 가져오기 위한 메소드
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(id);
		ArrayList<Feed> search = new ArrayList<Feed>();
		String sql = null;
		sql = "select * from feed where feed_id like ? order by feed_num desc";
		// ID를 조건으로 ID에 해당되는 게시글 전체를 List에 담기위한 쿼리문
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Feed feed = new Feed();
				feed.setFeed_num(rs.getInt(1));
				feed.setFeed_id(rs.getString(2));
				feed.setFeed_content(rs.getString(3));
				feed.setFeed_date(rs.getDate(4));
				feed.setFeed_like(rs.getInt(5));
				feed.setFeed_refuse(rs.getInt(6));

				search.add(feed);
			}

		} catch (Exception e) {
			System.out.println("select 오류!!" + e);
		} finally {
			close(rs);
			close(pstmt);

		}
		return search;
	}

	public ArrayList<Feed> feedCategory(int category, int sort) {
		// 특정 카테고리의 피드를 가져와 List에 담고 정렬시켜주는 쿼리문
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Feed> search = new ArrayList<Feed>();
		String sql = null;
		if(sort==1) {
			sql = "select * from feed where feed_class = ? order by feed_num desc";
			// 특정 카테고리의 피드를 가져오고 시간순으로 정렬하기위한 쿼리문
		}else {
			sql = "select * from feed where feed_class = ? order by feed_like desc, feed_num desc";
			// 특정 카테고리의 피드를 가져오고 추천순으로 정렬하기위한 쿼리문
		}		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, category);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Feed feed = new Feed();
				feed.setFeed_num(rs.getInt(1));
				feed.setFeed_id(rs.getString(2));
				feed.setFeed_content(rs.getString(3));
				feed.setFeed_date(rs.getDate(4));
				feed.setFeed_like(rs.getInt(5));
				feed.setFeed_refuse(rs.getInt(6));
				feed.setFeed_category(rs.getInt(7));

				search.add(feed);
			}

		} catch (Exception e) {
			System.out.println("select 오류!!" + e);
		} finally {
			close(rs);
			close(pstmt);

		}
		return search;
	}
}
