package model;

public class UserAccount {
	
	private String userName;
	private String passWord;
	private String photoURL;
	private String birthday;
	private Gender gender;
	private String career;
	private Browser browser;
	
	public UserAccount(String userName, String passWord, String photoURL, String gender, String career,String birthday,Browser browser) {
		this.userName=userName;
		this.passWord=passWord;
		this.photoURL=photoURL;
		setGender(gender);
		this.career=career;
		this.birthday=birthday;
		this.browser=browser;
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

	public void setGender(String gender) {
		switch(gender){
			case "Male" : this.gender=Gender.MALE;
			break;
			
			case "Female" : this.gender=Gender.FEMALE;
			break;
			
			case "Other" : this.gender=Gender.OTHER;
			break;
		}
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}
	
}
