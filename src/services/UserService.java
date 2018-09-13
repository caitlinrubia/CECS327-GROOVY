package services;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import data.models.*;

/**
 * 
 * 
 */
public class UserService {

	private String fPath = "./src/data/store.json";
	
	/**
	 * 
	 */
	public UserService() {
		//
	}
	
	/**
	 * 
	 * @return
	 */
	public List<User> getUsers() {
		
		// Create file object from store
		File file = new File(fPath);
		
		String json = "";
		
		// Read each character token in file
		try (FileInputStream in = new FileInputStream(file)) {
			
			int w;
			while ((w = in.read()) != -1) {
				
				json = json + (char) w;
				
			}
			
			// Identify token type for deserialization
			Gson gson = new Gson();
			Store store = gson.fromJson(json, new TypeToken<Store>() {}.getType());
			
			return store.getUsers();
			
		} catch(Exception e) {
			
			System.out.print(e);
			
			return Collections.emptyList();
			
		}
	}
	
	
	/**
	 * TEST FOR SEARCH IMPLEMENTATION
	 * @return
	 */
	public List<Playlist> getAllPlaylists() {
		
		// Create file object from store
		File file = new File(fPath);
				
		String json = "";
				
		// Read each character token in file
		try (FileInputStream in = new FileInputStream(file)) {
					
			List<Playlist> allPlaylists = new ArrayList<Playlist>();
			
			int w;
			while ((w = in.read()) != -1) {
						
				json = json + (char) w;
						
			}
					
			// Identify token type for deserialization
			Gson gson = new Gson();
			Store store = gson.fromJson(json, new TypeToken<Store>() {}.getType());
			
			List<User> allUsers = store.getUsers();
			
			for (User user: allUsers) {
				List<Playlist> userPlaylists = user.getPlaylists();
				for (Playlist playlist : userPlaylists) {
					allPlaylists.add(playlist);
				}
			}
			
			return allPlaylists;
					
		} catch(Exception e) {
					
			System.out.print(e);
					
			return Collections.emptyList();
					
		}
	}
}