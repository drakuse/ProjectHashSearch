package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FeedDAO;
import vo.Feed;

public class FeedMemberService {
	public ArrayList<Feed> feedMember(String id) {
		// feedDAO 싱글톤 객체 생성
		FeedDAO feedDAO = FeedDAO.getInstance();
		Connection con = getConnection();
		feedDAO.setConnection(con);

		ArrayList<Feed> feedList = new ArrayList<Feed>();

		feedList=feedDAO.feedMember(id);
		close(con);

		return feedList;
	}
}
