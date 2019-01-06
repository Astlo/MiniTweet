package tinyTwitter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

@Api(name = "apiTweet",
version = "v1")
public class ApiTweet {
	static {
		ObjectifyService.register(User.class);
		ObjectifyService.register(Message.class);			
	}

	@ApiMethod(name="addMessage", path = "addmsg")
	public User addMessage(@Named("userId") String username,@Named("userMsg") String message){
		long time1;
		long time2;
		time1 = System.currentTimeMillis();
		User usr = ofy().load().type(User.class).id(username).now();
		if(usr != null)
		{
			List<String> users = usr.getFollowers();
			Message e = new Message(message, users, username);
			Pattern p = Pattern.compile("#[A-Za-z]+");
		    Matcher m = p.matcher(message);
		    while(m.find()) {
		    	e.addHashTag(m.group());
		    }
			ofy().save().entities(e).now();
		}
		time2 = System.currentTimeMillis();
		return new User(Long.toString(time2 - time1));
	}
		
	@ApiMethod(name="timeline", path = "timeline")
	public List<Message> getTimeline(@Named("userID") String pseudo,@Named("nbDeMessages") int nbMessage) {
		long time1;
		long time2;
		List<Message> timeline = new ArrayList<Message>();
		time1 = System.currentTimeMillis();
		timeline = ofy().load().type(Message.class).filter("receivers ==", pseudo).order("-timestamp").limit(nbMessage).list();
		time2 = System.currentTimeMillis();
		List<Message> newList = new ArrayList<Message>();
		newList.add(new Message(Long.toString(time2 - time1),new ArrayList<String>(), "Temps"));
		newList.addAll(timeline);
		return newList;
	}
	
	@ApiMethod(name="hashtag", path = "hashtag")
	public List<Message> getTimelineHashtag(@Named("tag") String hashtag,@Named("nbDeMessages") int nbMessage) {
		long time1;
		long time2;
		List<Message> timeline = new ArrayList<Message>();
		time1 = System.currentTimeMillis();
		timeline = ofy().load().type(Message.class).filter("HashTag ==", hashtag).order("-timestamp").limit(nbMessage).list();
		time2 = System.currentTimeMillis();
		List<Message> newList = new ArrayList<Message>();
		newList.add(new Message(Long.toString(time2 - time1),new ArrayList<String>(), "Temps"));
		newList.addAll(timeline);
		return newList;
	}
	
	@ApiMethod(name="adduser", path = "adduser")
	public User addUser(@Named("username") String username){
	
		if(ofy().load().type(User.class).id(username).now() == null)
		{
			User e = new User(username);
			e.setFollowers(new ArrayList<String>());
			e.setFollows(new ArrayList<String>());
			ofy().save().entities(e).now();
			return e;
		} else {
			return null;
		}
	}
	
	@ApiMethod(name = "followunfollow", path = "fuf")
	public User followdefollow(@Named("userId") String username, @Named("followed") String followed) {
		if(ofy().load().type(User.class).id(username).now() != null && ofy().load().type(User.class).id(followed).now() != null) {
			if(ofy().load().type(User.class).id(username).now().getFollows().contains(followed)) {

				User user1 = ofy().load().type(User.class).id(username).now();
				user1.removeFollow(followed);
				ofy().save().entities(user1).now();

				User user2 = ofy().load().type(User.class).id(followed).now();
				user2.removeFollower(username);
				ofy().save().entities(user2).now();
				
				for	(Message item : ofy().load().type(Message.class).filter("sender ==", followed).list()) {
					Message msg = item;
					msg.removeReceivers(username);
					ofy().save().entities(msg).now();
				}
				
				User user3 = new User("dejafollow");
				return user3;
			} else {
				User user1 = ofy().load().type(User.class).id(username).now();
				user1.addFollow(followed);
				ofy().save().entities(user1).now();

				User user2 = ofy().load().type(User.class).id(followed).now();
				user2.addFollower(username);
				ofy().save().entities(user2).now();
				
				for(Message item : ofy().load().type(Message.class).filter("sender ==", followed).list()) {
					
					Message msg = item;
					msg.addReceivers(username);
					ofy().save().entities(msg).now();
					
				}
				User user3 = new User("pasfollow");
				return user3;
			}
		} else {
			User user3 = new User("userexistepas");
			return user3;
		}
		
	}
	
	
	@ApiMethod(name = "unfollow", path = "unfollow")
	public void unfollow(@Named("userId") String username, @Named("followed") String followed) {
		if(ofy().load().type(User.class).id(username).now() != null && ofy().load().type(User.class).id(followed).now() != null) {
			User user1 = ofy().load().type(User.class).id(username).now();
			user1.removeFollow(followed);
			ofy().save().entities(user1).now();

			User user2 = ofy().load().type(User.class).id(followed).now();
			user2.removeFollower(username);
			ofy().save().entities(user2).now();
			
			for	(Message item : ofy().load().type(Message.class).filter("sender ==", followed).list()) {
				Message msg = item;
				msg.removeReceivers(username);
				ofy().save().entities(msg).now();
			}
		}
	}
	
	@ApiMethod(name = "follow", path = "follow")
	public void follow(@Named("userId") String username, @Named("followed") String followed) {
		if(ofy().load().type(User.class).id(username).now() != null && ofy().load().type(User.class).id(followed).now() != null) {
			User user1 = ofy().load().type(User.class).id(username).now();
			user1.addFollow(followed);
			ofy().save().entities(user1);

			User user2 = ofy().load().type(User.class).id(followed).now();
			user2.addFollower(username);
			ofy().save().entities(user2);
			
			for(Message item : ofy().load().type(Message.class).filter("sender ==", followed).list()) {
				Message msg = item;
				msg.addReceivers(username);
				ofy().save().entities(msg);
			}
		}
	}
	
	@ApiMethod(name = "listfollows", path = "listfollow", description = "Methode de test")
	public List<String> listFollows(@Named("userId") String username){
		return ofy().load().type(User.class).id(username).now().getFollows();
	}
	
	@ApiMethod(name = "listfollowers", path = "listfollower", description = "Methode de test")
	public List<String> listFollowers(@Named("userId") String username){
		return ofy().load().type(User.class).id(username).now().getFollowers();
	}
	

	@ApiMethod(name = "listmessages", description = "Methode de test")
	public List<Message> listMessages(){
		return ofy().load().type(Message.class).list();
	}


	@ApiMethod(name = "deleteuser", description = "Methode de test")
	public void deleteUser(@Named("userId") String username){
		List<Key<Message>> keys = ofy().load().type(Message.class).filter("sender", username).keys().list();
		ofy().delete().keys(keys).now();
		ofy().delete().type(User.class).id(username);
	}
	
	@ApiMethod(name = "listusers", description = "Methode de test")
	public List<User> listUsers(){
		return ofy().load().type(User.class).list();
	}
	
	@ApiMethod(name = "deleteMessages", description = "Methode de test")
	public void deleteUsers(){
		List<Key<Message>> keys = ofy().load().type(Message.class).keys().list();
		ofy().delete().keys(keys).now();
	}
	
}