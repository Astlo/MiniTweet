package tinyTwitter;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class User {
	
	private @Id	String idUser;
	
	private List<String> follows = new ArrayList<String>();
	
	private List<String> followers = new ArrayList<String>();
	
	public User() {}

	public User(String username) {
		this.idUser = username;
	}
	
	public List<String> getFollows() {
		return follows;
	}
	
	public List<String> getFollowers() {
		return followers;
	}

	public void setFollows(List<String> follows) {
		this.follows = follows;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public void addFollow(String e)
	{
		if(this.follows == null) {
			this.follows = new ArrayList<String>(); 
		}
		if(!this.follows.contains(e))
		{
			this.follows.add(e);
		}
	}
	
	public void removeFollower(String e){
		this.followers.remove(e);
	}

	public void removeFollow(String e)
	{
		this.follows.remove(e);
	}
	
	public void addFollower(String e){
		if(this.followers == null) {
			this.followers = new ArrayList<String>();
		}
		if(!this.followers.contains(e))
		{
			this.followers.add(e);
		}
	}

	public String getId() {
		return idUser;
	}

	public void setId(String idUser) {
		this.idUser = idUser;
	}

}