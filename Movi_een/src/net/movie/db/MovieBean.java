package net.movie.db;

public class MovieBean {
	
	int MOVIE_ID;
	int MOVIE_PUB_YEAR;
	String MOVIE_GENRE;
	String MOVIE_TITLE;
	String MOVIE_TITLE_EN;
	
	public String getMOVIE_TITLE_EN() {
		return MOVIE_TITLE_EN;
	}
	public void setMOVIE_TITLE_EN(String mOVIE_TITLE_EN) {
		MOVIE_TITLE_EN = mOVIE_TITLE_EN;
	}
	public int getMOVIE_ID() {
		return MOVIE_ID;
	}
	public void setMOVIE_ID(int mOVIE_ID) {
		MOVIE_ID = mOVIE_ID;
	}
	public int getMOVIE_PUB_YEAR() {
		return MOVIE_PUB_YEAR;
	}
	public void setMOVIE_PUB_YEAR(int mOVIE_PUB_YEAR) {
		MOVIE_PUB_YEAR = mOVIE_PUB_YEAR;
	}
	
	public String getMOVIE_GENRE() {
		return MOVIE_GENRE;
	}
	public void setMOVIE_GENRE(String mOVIE_GENRE) {
		MOVIE_GENRE = mOVIE_GENRE;
	}
	public String getMOVIE_TITLE() {
		return MOVIE_TITLE;
	}
	public void setMOVIE_TITLE(String mOVIE_TITLE) {
		MOVIE_TITLE = mOVIE_TITLE;
	}
	
}
