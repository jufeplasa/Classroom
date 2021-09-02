package model;

import java.time.LocalDate;

public class UserAccount {
	
	private String userName;
	private String passWord;
	private String photoURL;
	private LocalDate birthday;
	private Gender gender;
	private String career;
	private Browser browser;
	
	public UserAccount(String userName, String passWord, String photoURL, Gender gender, String career,LocalDate birthday,String browser) {
		this.userName=userName;
		this.passWord=passWord;
		this.photoURL=photoURL;
		setGender(gender);
		this.career=career;
		this.birthday=birthday;
		setBrowser(browser);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		switch (browser) {

		case "CHROME":
			this.browser = Browser.CHROME;
			break;

		case "EDGE":
			this.browser =Browser.EDGE;
			break;

		case "FIREFOX":
			this.browser = Browser.FIREFOX;
			break;

		case "OPERA":
			this.browser =Browser.OPERA;
			break;
		}
	}


	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public LocalDate getBirthday() {
		return birthday;
	}
	
}
