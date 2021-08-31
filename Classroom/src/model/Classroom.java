package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	
	private List<UserAccount> user;
	
	public Classroom() {
		user= new ArrayList<UserAccount>();
		user.add(new UserAccount("jufeplasa", "Juanfeplaza17", "file:///C:/Users/Juan%20Felipe%20Plaza/OneDrive/Imágenes/Jufeplasa3D.png", "Male", "Industrial Engineering", "23/03/2002", Browser.OPERA));
	}

	public List<UserAccount> getUser() {
		return user;
	}

	public void setUser(List<UserAccount> user) {
		this.user = user;
	}
	
	public boolean verification(String username, String password) {
		for(int i=0;i<user.size();i++) {
			if(username.equals(user.get(i).getUserName())&&password.equals(user.get(i).getPassWord())){
				
			}
		}
		return true;
	}


	public boolean add(UserAccount newUser) {
		
		if(user.add(newUser)) {
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	
}
