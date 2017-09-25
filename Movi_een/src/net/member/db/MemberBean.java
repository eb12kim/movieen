package net.member.db;

public class MemberBean {
	
	private String MEMBER_ID;
	private String MEMBER_PW;
	private String MEMBER_PHONE;
	private String MEMBER_NAME;
	private int MEMBER_AGE;
	private int MEMBER_GENDER;
	private String MEMBER_NATIONALITY;
	private String[] MEMBER_MOVIE_LIKE;
	private String[] MEMBER_TRIP_LIKE;
	
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getMEMBER_PW() {
		return MEMBER_PW;
	}
	public void setMEMBER_PW(String mEMBER_PW) {
		MEMBER_PW = mEMBER_PW;
	}
	public String getMEMBER_PHONE() {
		return MEMBER_PHONE;
	}
	public void setMEMBER_PHONE(String mEMBER_PHONE) {
		MEMBER_PHONE = mEMBER_PHONE;
	}
	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}
	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}
	public int getMEMBER_AGE() {
		return MEMBER_AGE;
	}
	public void setMEMBER_AGE(int mEMBER_AGE) {
		MEMBER_AGE = mEMBER_AGE;
	}
	public int getMEMBER_GENDER() {
		return MEMBER_GENDER;
	}
	public void setMEMBER_GENDER(int mEMBER_GENDER) {
		MEMBER_GENDER = mEMBER_GENDER;
	}
	public String getMEMBER_NATIONALITY() {
		return MEMBER_NATIONALITY;
	}
	public void setMEMBER_NATIONALITY(String mEMBER_NATIONALITY) {
		MEMBER_NATIONALITY = mEMBER_NATIONALITY;
	}
	public String[] getMEMBER_MOVIE_LIKE() {
		return MEMBER_MOVIE_LIKE;
	}
	public void setMEMBER_MOVIE_LIKE(String[] mEMBER_MOVIE_LIKE) {
		MEMBER_MOVIE_LIKE = mEMBER_MOVIE_LIKE;
	}
	public String[] getMEMBER_TRIP_LIKE() {
		return MEMBER_TRIP_LIKE;
	}
	public void setMEMBER_TRIP_LIKE(String[] mEMBER_TRIP_LIKE) {
		MEMBER_TRIP_LIKE = mEMBER_TRIP_LIKE;
	}
	public String printMEMBER_MOVIE_LIKE() {
        String res = "";
        if(MEMBER_MOVIE_LIKE!=null) {
        	for(int i=0; i<MEMBER_MOVIE_LIKE.length; i++) {
        		res += MEMBER_MOVIE_LIKE[i]+" ";
        	}
        } else {
        	return "없습니다. 지금 추가해보세요!";
        }
        return res;
    }
    public String printMEMBER_TRIP_LIKE() {
        String res = "";
        if(MEMBER_TRIP_LIKE!=null) {
        	for(int i=0; i<MEMBER_TRIP_LIKE.length; i++) {
        		res += MEMBER_TRIP_LIKE[i]+" ";
        	}
        } else {
        	return "없습니다. 지금 추가해보세요!";
        }
        return res;
    }
}
