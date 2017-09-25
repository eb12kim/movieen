package net.post.db;

import java.util.Date;

public class ReplyBean {
	
	private int REPLY_NO;
	private int REPLY_POST_NO;
	private int REPLY_LEV;
	private String REPLY_TEXT;
	private String REPLY_USER_ID;
	private Date REPLY_DATE;
	
	public int getREPLY_NO() {
		return REPLY_NO;
	}
	public void setREPLY_NO(int rEPLY_NO) {
		REPLY_NO = rEPLY_NO;
	}
	public int getREPLY_POST_NO() {
		return REPLY_POST_NO;
	}
	public void setREPLY_POST_NO(int rEPLY_POST_NO) {
		REPLY_POST_NO = rEPLY_POST_NO;
	}
	public int getREPLY_LEV() {
		return REPLY_LEV;
	}
	public void setREPLY_LEV(int rEPLY_LEV) {
		REPLY_LEV = rEPLY_LEV;
	}
	public String getREPLY_TEXT() {
		return REPLY_TEXT;
	}
	public void setREPLY_TEXT(String rEPLY_TEXT) {
		REPLY_TEXT = rEPLY_TEXT;
	}
	public String getREPLY_USER_ID() {
		return REPLY_USER_ID;
	}
	public void setREPLY_USER_ID(String rEPLY_USER_ID) {
		REPLY_USER_ID = rEPLY_USER_ID;
	}
	public Date getREPLY_DATE() {
		return REPLY_DATE;
	}
	public void setREPLY_DATE(Date rEPLY_DATE) {
		REPLY_DATE = rEPLY_DATE;
	}
	
}
