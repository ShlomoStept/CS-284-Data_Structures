package sa1;

import java.util.*;

public class Player {

	private static class Node<F> {
		private F data;
		private Node<F> next;
		
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		public Node(F data) {
			super();
			this.data = data;
		}
	}
	
	private Node<Song> currentSong;	//The currently playing / first song in the queue
	private Node<Song> lastSong;	//The last song in the queue
	private int playQueueSize;		//The total amount of songs in the play queue
	private int totalRunTime;		//The amount of time the player has played; intended to only go up

	public Player() {
		//TODO
	}
	
	public Player(Playlist plist) {
		//TODO
	}
	
	public int getTotalRuntime() {
		//TODO
	}
	
	public int getPlayQueueSize() {
		//TODO
	}
	
	public Song getCurrentSong() {
		//TODO
	}

	public ArrayList<Song> getAllSongs(){
		//TODO
	}
	
	public int getRemainingRunTime() {
		//TODO
	}
	
	public void playNextSong() {
		//TODO
	}
	
	public void clearQueue() {
		//TODO
	}
	
	public void addSong(Song song) {
		//TODO
	}
}
