package service;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.FeedDAO;

public class FeedDeleteService {

	public boolean FeedDelete(String feedNum, String feedId, String feedPass) {
		// feedDAO 싱글톤 객체 생성
		FeedDAO feedDAO = FeedDAO.getInstance();
		Connection con = getConnection();
		feedDAO.setConnection(con);

		boolean FeedDeleteResult = false;

		int result = feedDAO.feedDelete(feedNum, feedId, feedPass);
		System.out.println(result);
		if (result > 0) {
			FeedDeleteResult = true;
			commit(con);
		} else {
			FeedDeleteResult = false;
			rollback(con);
		}
		return FeedDeleteResult;
	}

}
