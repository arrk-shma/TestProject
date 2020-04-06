package trials;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class First {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			
		// This can be done with only Gson object , but Gson builder will give pretty print feature
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		FriendPOJO friend = new FriendPOJO();
		friend.setFirstName("Mahesh");
		friend.setLastName("Tandel");
		friend.setAge(41);
		friend.setAddress("Old Panvel");
		
		//FriendPOJO friend = getFriend();
		String json = gson.toJson(friend);
		System.out.println(json);
		
	    try (FileWriter writer = new FileWriter(".\\1.json")) {
            gson.toJson(friend, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
		

	}
	
/*	private static FriendPOJO getFriend(){
		
		FriendPOJO friend = new FriendPOJO();
		friend.setFirstName("Mahesh");
		friend.setLastName("Tandel");
		friend.setAge(41);
		friend.setAddress("Old Panvel");
		return friend;
		
		
	}*/

}
