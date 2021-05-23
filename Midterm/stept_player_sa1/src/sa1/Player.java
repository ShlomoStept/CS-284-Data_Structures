/* 	Shlomo Stept
 * "I pledge my honor that I have abided by the Stevens Honor System"
 *
 *  CS284: Lecture A; Recitation A
 *
 *  Special Assignment 1
 */

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


//---------------------------------------------------------------------------------------------------------------------
	// Data Fields
//---------------------------------------------------------------------------------------------------------------------

	private Node<Song> currentSong;	//The currently playing / first song in the queue
	private Node<Song> lastSong;	//The last song in the queue
	private int playQueueSize;		//The total amount of songs in the play queue
	private int totalRunTime;		//The amount of time the player has played; intended to only go up


//---------------------------------------------------------------------------------------------------------------------
	//Constructors
//---------------------------------------------------------------------------------------------------------------------

		//  --> 1. Player: creates an empty player
	//-------------------------------------------------------------------------------------
	public Player() {
		this.currentSong = new Node<Song>(null);
		this.lastSong = new Node<Song>(null);
		playQueueSize = 0;
		totalRunTime = 0;
	}


		//  --> 2. Player: creates an player from the given playlist with the same order as the playlist, i.e the first in the playlist will be the first in the queue
		    //		--> Note:: if the playlist is empty an exception is thrown
	//-------------------------------------------------------------------------------------
	public Player(Playlist plist) {


		if (plist.getSize() == 0 )
		{
			throw new IllegalArgumentException("Player: playlist is empty");
		}

		Node<Song> current = new Node<Song>(null);
		for (int i =0; i< plist.getSize(); i++)
		{
			if(i == 0)
			{
				currentSong = new Node<Song>(plist.getAt(i));
				totalRunTime = currentSong.data.getRunTime();
				playQueueSize ++;
				current = currentSong;
			}
			else
			{
				current.next = new Node<Song>(plist.getAt(i));
				playQueueSize ++;
				current = current.next;
			}
		}

		lastSong = current;

	}

//---------------------------------------------------------------------------------------------------------------------
	// Methods:
//---------------------------------------------------------------------------------------------------------------------

		//  --> 1. getTotalRuntime: returns the total runtime of the songs which have been played so far -- for this player
	//-------------------------------------------------------------------------------------

	public int getTotalRuntime()
	{
		return totalRunTime;
	}


		//  --> 2. getPlayQueueSize: returns the total amount of songs in the queue including the one being played
	//-------------------------------------------------------------------------------------
	public int getPlayQueueSize()
	{
		/*Node<Song> current = currentSong;
		int QueueSize =0;
		while(current != null && current.data != null)
		{
			QueueSize++;
			current = current.next;
		}
		return QueueSize;*/

		return playQueueSize;
	}


		//  --> 3. getCurrentSong: returns the currentSong, i.e. the song being played currently
		   // 		->  Note:: if there is no song being played it throws an exception
	//-------------------------------------------------------------------------------------
	public Song getCurrentSong() {
		if(playQueueSize == 0)
		{
			throw new IllegalArgumentException("getCurrentSong: No song currently playing");
		}

		return currentSong.data;
	}



		//  --> 4. getAllSongs: returns an ArrayList of all the songs in the playlist
		   // 		->  Note:: if there are no songs in the play queue an exception is thrown
	//-------------------------------------------------------------------------------------
	public ArrayList<Song> getAllSongs(){
		if(playQueueSize == 0)
		{
			throw new IllegalStateException("getAllSongs: No songs in the play queue");
		}
		Node<Song> current = currentSong;
		ArrayList<Song> playlist = new ArrayList<Song>();
		while(current != null)
		{
			playlist.add(current.data);
			current = current.next;
		}

		return playlist;

	}


		//  --> 5. getRemainingRunTime: returns the remaining runtime for the songs in the queue, i.e. all songs after current in the queue
		    // 		->  Note:: if there are No songs in the play queue an exception is thrown
	//-------------------------------------------------------------------------------------
	public int getRemainingRunTime() {
		if(playQueueSize == 0)
		{
			throw new IllegalStateException("getRemainingRunTime: No song in the play queue");
		}

		Node<Song> current = currentSong.next;
		int RemainingRunTime = 0;

		while(current != null && current.data!=null)
		{
			// QQQ is current not null but current.data ever null--> so far the only way ive been able to crate this senario is if i alter a method, or create a node specially like that independant to the methods another class would have access too
			RemainingRunTime += current.data.getRunTime();

			current = current.next;
		}

		return RemainingRunTime;
	}


		//  --> 6.  playNextSong: plays the next song in the queue
			 //   ->  Note:: if currentSong is null/empty an exception is thrown
	//-------------------------------------------------------------------------------------
	public void playNextSong() {
		if(currentSong ==null || currentSong.data ==null)
		{
			throw new IllegalStateException("playNextSong: No song is currently being played");
		}

		if(currentSong.next != null)
		{
			currentSong = currentSong.next;
			totalRunTime += currentSong.data.getRunTime();
			playQueueSize--;
		}
		else
		{
			currentSong = null;
			playQueueSize--;
		}
	}


		//  --> 7. clearQueue: clear the play queue of the player
	//-------------------------------------------------------------------------------------
	public void clearQueue() {
		currentSong = new Node<Song>(null);
		lastSong = new Node<Song>(null);
		playQueueSize = 0;
	}



		//  --> 8. addSong: adds a song to the end of the play queue
			//	  --> and if the queue is empty it adds it the current song
				// ->> all the additions make sure to properly link the lastsong and increment the playQueue size
	//-------------------------------------------------------------------------------------

	public void addSong(Song song) {
		if(getPlayQueueSize() == 0)
		{
			currentSong = new Node<Song>(song);
			lastSong = currentSong;
			// i.e if a song is playing does tht mean that its already played counted in the totalruntime????
			totalRunTime += currentSong.data.getRunTime();
			playQueueSize++;

		}
		else {
			Node<Song> current = currentSong;

			while(current!= null && current.next!= null)
			{
				current = current.next;
			}

			current.next = new Node<Song>(song);
			lastSong = current.next;
			playQueueSize++;


		}
	}
	public static void main(String[] args)
    {
    }
 
}
