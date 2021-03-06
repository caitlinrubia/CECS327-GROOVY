package api.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import data.constants.Files;
import data.index.MetadataFile;
import data.models.Song;

import java.util.Set;
import java.util.TreeMap;

import net.tomp2p.peers.Number160;
import services.MetadataFileService;

public class MetadataServiceMap {

	List<Peer> peers;

	List<String> results;

	public MetadataServiceMap(List<Peer> peers) {
		this.peers = peers;

	}

	public List<Song> search(int type, String query) throws IOException {
		
		List<String> values = new ArrayList<String>();
		// peer 0-2 has songs.txt peer 3-5 has artists.txt peer 6-8 has albums.txt
		switch (type) {
		case Files.ALBUMTYPE:
			System.out.println("SEARCHING ALBUM");
			for (int i = 0; i < 3; i++) {

				values.addAll(peers.get(i).reduce(query));
			}
			break;

		case Files.ARTISTTYPE:
			System.out.println("SEARCHING ARTIST");
			for (int j = 3; j < 6; j++) {
				values.addAll(peers.get(j).reduce(query));
			}
			break;

		case Files.SONGTYPE:
			System.out.println("SEARCHING SONG");

			for (int k = 6; k < 9; k++) {
				values.addAll(peers.get(k).reduce(query));
			}
			break;

		}
		List <Song> results= songSearch(type, values);
		
		// RETURN A LIST<SONG>
		return results;
	}
	
	//Converting list of strings to list of songs based on type
	public List<Song> songSearch(int type, List <String> songList) {
		List<Song> songs = new ArrayList<Song>();
		for(String s: songList) {
			String[] songInfo = s.split(";");
			Song song = new Song();
			switch(type){
			case Files.ALBUMTYPE:
				System.out.println("FIND ALBUM");
				song.setSongID(Integer.parseInt(songInfo[Files.SONGID[Files.ALBUMTYPE]]));
				song.setTitle(songInfo[Files.TITLE[Files.ALBUMTYPE]]);
				song.setArtist(songInfo[Files.ARTIST[Files.ALBUMTYPE]]);
				song.setAlbum(songInfo[Files.ALBUM[Files.ALBUMTYPE]]);
				song.setDuration(Double.parseDouble(songInfo[Files.DURATION[Files.ALBUMTYPE]]));
				break;

			case Files.ARTISTTYPE:
				System.out.println("FIND ARTIST");
				song.setSongID(Integer.parseInt(songInfo[Files.SONGID[Files.ARTISTTYPE]]));
				song.setTitle(songInfo[Files.TITLE[Files.ARTISTTYPE]]);
				song.setArtist(songInfo[Files.ARTIST[Files.ARTISTTYPE]]);
				song.setAlbum(songInfo[Files.ALBUM[Files.ARTISTTYPE]]);
				song.setDuration(Double.parseDouble(songInfo[Files.DURATION[Files.ARTISTTYPE]]));
				break;

			case Files.SONGTYPE:
				song.setSongID(Integer.parseInt(songInfo[Files.SONGID[Files.SONGTYPE]]));
				song.setTitle(songInfo[Files.TITLE[Files.SONGTYPE]]);
				song.setArtist(songInfo[Files.ARTIST[Files.SONGTYPE]]);
				song.setAlbum(songInfo[Files.ALBUM[Files.SONGTYPE]]);
				song.setDuration(Double.parseDouble(songInfo[Files.DURATION[Files.SONGTYPE]]));
				break;
			}
			songs.add(song);
		}
		return songs;
	}
	

	public void mapContext(File file, Peer peer, Counter counter) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line = reader.readLine();

			while (line != null) {
				// read line by line execute mapper.map(key, value, counter)
				peer.map(line);

				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// when its done complete file call counter increment(page n)
		// what's the key here?
		// counter.increment(1);

	}

	// CAITLIN
	public void reduceContext(String search, Peer peer, Counter counter) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					List<String> results = peer.reduce(search);
					counter.increment(peer.getID(), results.size());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		thread.run();

	}

	public void initMap() {
		Counter mapCounter = new Counter();

		// Initialize service to deserialize metadata.json
		MetadataFileService mfs = new MetadataFileService();

		// Deserialize metadata.json
		List<MetadataFile> mdf = mfs.getMetadataFile();

		// Assign a peer to map each file in metadata
		int index = 0;
		for (int i = 0; i < Files.FILES.length; i++) {
			File file = new File(Files.FILES[i]);
			Peer mapper = peers.get(index);
			mapContext(file, mapper, mapCounter);
			index++;
		}

		System.out.println("Complete");
	}

}
