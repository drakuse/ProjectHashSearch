package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.FeedDAO;

public class RefuseService {
	public boolean refuseCount(String feed_num, String member_id, String feed_refuse) {
		// feedrDAO 싱글톤 객체 생성
		FeedDAO feedDAO = FeedDAO.getInstance();
		Connection con = getConnection();
		feedDAO.setConnection(con);

		boolean countResult = false;

		int result = feedDAO.refuseCount(feed_num, member_id, feed_refuse);
		if (result > 0) {
			countResult = true;
			commit(con);
		} else {
			countResult = false;
			rollback(con);
		}
		close(con);

		return countResult;
		
	}
}