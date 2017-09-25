package net.post.db;

public class MapBean {

	int POST_NO;
	String POST_SUBJECT;
	int POST_MOVIE_ID;
	String MOVIE_TITLE;
	float POST_GPS_LAT;
	float POST_GPS_LNG;
	String POST_NATION;
	String POST_FILE;
	
	public int getPOST_NO() {
		return POST_NO;
	}

	public void setPOST_NO(int pOST_NO) {
		POST_NO = pOST_NO;
	}

	public String getPOST_SUBJECT() {
		return POST_SUBJECT;
	}

	public void setPOST_SUBJECT(String pOST_SUBJECT) {
		POST_SUBJECT = pOST_SUBJECT;
	}

	public int getPOST_MOVIE_ID() {
		return POST_MOVIE_ID;
	}

	public void setPOST_MOVIE_ID(int pOST_MOVIE_ID) {
		POST_MOVIE_ID = pOST_MOVIE_ID;
	}

	public String getMOVIE_TITLE() {
		return MOVIE_TITLE;
	}

	public void setMOVIE_TITLE(String mOVIE_TITLE) {
		MOVIE_TITLE = mOVIE_TITLE;
	}

	public float getPOST_GPS_LAT() {
		return POST_GPS_LAT;
	}

	public void setPOST_GPS_LAT(float pOST_GPS_LAT) {
		POST_GPS_LAT = pOST_GPS_LAT;
	}

	public float getPOST_GPS_LNG() {
		return POST_GPS_LNG;
	}

	public void setPOST_GPS_LNG(float pOST_GPS_LNG) {
		POST_GPS_LNG = pOST_GPS_LNG;
	}

	public String getPOST_NATION() {
		return POST_NATION;
	}

	public void setPOST_NATION(String pOST_NATION) {
		POST_NATION = pOST_NATION;
	}

	public String getPOST_FILE() {
		return POST_FILE;
	}

	public void setPOST_FILE(String pOST_FILE) {
		POST_FILE = pOST_FILE;
	}

}
