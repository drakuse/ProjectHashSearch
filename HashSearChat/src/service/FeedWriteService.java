package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.FeedDAO;
import vo.Feed;

public class FeedWriteService {

	public boolean feedWrite(Feed feed) {
		// feedDAO 싱글톤 객체 생성
		FeedDAO feedDAO = FeedDAO.getInstance();
		Connection con = getConnection();
		feedDAO.setConnection(con);

		boolean writeResult = false;

		int result = feedDAO.feedWrite(feed);
		if (result > 0) {
			writeResult = true;
			commit(con);
		} else {
			writeResult = false;
			rollback(con);
		}
		close(con);

		return writeResult;
	}
}
