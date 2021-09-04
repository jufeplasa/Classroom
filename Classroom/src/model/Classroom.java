package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Classroom {
	private static final String DEFAULTIMAGE="\"file:///C:/Users/Juan%20Felipe%20Plaza/OneDrive/Imágenes/avatars-000396582750-afqhbt-t240x240.jpg\"";
	private List<UserAccount> user;
	
	public Classroom() {
		user= new ArrayList<UserAccount>();
		user.add(new UserAccount("jufeplasa", "Juanfeplaza17", "file:///C:/Users/Juan%20Felipe%20Plaza/OneDrive/Imágenes/Jufeplasa3D.png", Gender.MALE, "Industrial",LocalDate.of(2002,03,23), "EDGE"));
	}

	public List<UserAccount> getUser() {
		return user;
	}

	public void setUser(List<UserAccount> user) {
		this.user = user;
	}
	
	public boolean verification(String username, String password) {
		for(int i=0;i<user.size();i++) {
			if(username.equalsIgnoreCase(user.get(i).getUserName())&&password.equals(user.get(i).getPassWord())){
				return true;
			}

		}
		return false;
		
	}

	public String putImage(String username) {
		String perfilImage=DEFAULTIMAGE;
		for(int i=0;i<user.size();i++) {
			if(username.equalsIgnoreCase(user.get(i).getUserName())){
				perfilImage=user.get(i).getPhotoURL();
				return perfilImage;
			}
		}
		return perfilImage;
	}

	public boolean add(UserAccount newUser) {
		for(int i=0;i<user.size();i++) {
			if(newUser.getUserName().equalsIgnoreCase(user.get(i).getUserName())){
				return false;
			}
		}
		
		if(user.add(newUser)) {
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	
}
