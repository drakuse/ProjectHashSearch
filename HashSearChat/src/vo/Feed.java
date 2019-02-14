package vo;

import java.sql.Date;

public class Feed {
	private int feed_num;
	private String feed_id;
	private String feed_content;
	private Date feed_date;
	private int feed_like;
	private int feed_refuse;
	private int feed_category;

	public int getFeed_num() {
		return feed_num;
	}

	public void setFeed_num(int feed_num) {
		this.feed_num = feed_num;
	}

	public String getFeed_id() {
		return feed_id;
	}

	public void setFeed_id(String feed_id) {
		this.feed_id = feed_id;
	}

	public String getFeed_content() {
		return feed_content;
	}

	public void setFeed_content(String feed_content) {
		this.feed_content = feed_content;
	}

	public Date getFeed_date() {
		return feed_date;
	}

	public void setFeed_date(Date feed_date) {
		this.feed_date = feed_date;
	}

	public int getFeed_like() {
		return feed_like;
	}

	public void setFeed_like(int feed_like) {
		this.feed_like = feed_like;
	}

	public int getFeed_refuse() {
		return feed_refuse;
	}

	public void setFeed_refuse(int feed_refuse) {
		this.feed_refuse = feed_refuse;
	}

	public int getFeed_category() {
		return feed_category;
	}

	public void setFeed_category(int feed_category) {
		this.feed_category = feed_category;
	}
}
