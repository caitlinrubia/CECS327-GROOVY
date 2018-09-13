package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import api.AudioPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;

public class MainAppController implements Initializable {
	@FXML
	private ListView playlistScreen;
	@FXML
	private ImageView addPlaylist;
	@FXML
	private ImageView deletePlaylist;
	@FXML
	private ImageView playMusic;
	@FXML
	private ImageView previousMusic;
	@FXML
	private ImageView nextMusic;
	@FXML
	private ImageView stopMusic;
	@FXML
	private ImageView Mute;
	@FXML
	private Text songName;
	@FXML
	private Text songName1;
	@FXML
	private Label userNameText;
	@FXML
	private ImageView exit;
	@FXML
	private TableView Result;
	@FXML
	private TableColumn col1;
	@FXML
	private TableColumn col2;
	@FXML
	private TableColumn col3;
	@FXML
	private TableColumn col4;
	@FXML
	private TableColumn col5;
	
	
	// search buttons
	@FXML
	private ImageView btnAlbum;
	@FXML
	private ImageView btnSong;
	@FXML
	private ImageView btnArtist;
	
	// search bar 
	
	@FXML
	private TextField txtSearch;
	

	
	// we need a search function to update the result page !
	// make if song button is clicked then 
		
	@FXML
	public void btnSong(ActionEvent event) throws IOException {
	search(txtSearch.getText(), "song");
		
	}
	// make if album button is clicked then 
	@FXML
	public void btnAlbum(ActionEvent event) throws IOException {
	search(txtSearch.getText(), "album");
	}
	
	// make if artist button is clicked then 
	@FXML
	public void btnArtist(ActionEvent event) throws IOException {
	search(txtSearch.getText(), "artist");
	}
	
	
	private void search(String text, String type) {
		// TODO Auto-generated method stub
		if(type == "song") {
			//update result page to search for that song
			
		} else if(type == "album") {
			//update result page to search for that album
			
		} else if(type == "artist") {
			//update result page to search for that artist
			
		}
		
	}

	// audio player
	
	AudioPlayer player = new AudioPlayer();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		//TODO Display playlist name through "database";  display search result; 
		
		
	}

	// Event Listener on ImageView[#addPlaylist].onMouseClicked
	@FXML
	public void addPlaylistClicked(MouseEvent event) 
	{
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#deletePlaylist].onMouseClicked
	@FXML
	public void deletePlaylistClicked(MouseEvent event) 
	{
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#playMusic].onMouseClicked
	@FXML
	public void playMusicClicked(MouseEvent event) 
	{
		// TODO Autogenerated
		player.Play();
	}
	// Event Listener on ImageView[#previousMusic].onMouseClicked
	@FXML
	public void previousIsClicked(MouseEvent event) 
	{
		// TODO Autogenerated
		
		player.Previous();
	}
	// Event Listener on ImageView[#nextMusic].onMouseClicked
	@FXML
	public void nextIsClicked(MouseEvent event) 
	{
		// TODO Autogenerated
		
		player.Next();
	}
	// Event Listener on ImageView[#stopMusic].onMouseClicked
	@FXML
	public void musicStopClicked(MouseEvent event) 
	{
		// TODO Autogenerated
		player.Pause();
	}
	// Event Listener on ImageView[#Mute].onMouseClicked
	@FXML
	public void muteIsClicked(MouseEvent event) 
	{
		// TODO Autogenerated
		
		player.setVolume(0);
	}
	// Event Listener on ImageView[#exit].onMouseClicked
	@FXML
	public void exitIsClicked(MouseEvent event) 
	{
		// TODO Autogenerated
	}
	
	
	
}
