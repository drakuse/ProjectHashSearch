package service;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.FeedDAO;

public class FeedModifyService {
	public boolean FeedModify(String feedNum, String feedId, String feedPass, String feedContent) {

		// feedDAO 싱글톤 객체 생성
		FeedDAO feedDAO = FeedDAO.getInstance();
		Connection con = getConnection();
		feedDAO.setConnection(con);

		boolean FeedModifyResult = false;

		int result = feedDAO.feedModify(feedNum, feedId, feedPass, feedContent);

		if (result > 0) {
			FeedModifyResult = true;
			commit(con);
		} else {
			FeedModifyResult = false;
			rollback(con);
		}
		return FeedModifyResult;
	}
}
