package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.FeedDAO;

public class LikeService {
	public boolean likeCount(String feed_num, String member_id, String feed_like) {
		// memberDAO 싱글톤 객체 생성
		FeedDAO feedDAO = FeedDAO.getInstance();
		Connection con = getConnection();
		feedDAO.setConnection(con);

		boolean countResult = false;

		int result = feedDAO.likeCount(feed_num, member_id, feed_like);
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