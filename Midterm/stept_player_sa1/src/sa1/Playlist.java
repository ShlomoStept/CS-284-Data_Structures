/* 	Shlomo Stept
 * "I pledge my honor that I have abided by the Stevens Honor System"
 *
 *  CS284: Lecture A; Recitation A
 *
 *  Special Assignment 1
 */
package sa1;

public class Playlist{

//---------------------------------------------------------------------------------------------------------------------
	// Data Fields
//---------------------------------------------------------------------------------------------------------------------

	private SLL<Song> songs;


//---------------------------------------------------------------------------------------------------------------------
	//Constructor
//---------------------------------------------------------------------------------------------------------------------

		//  --> 1. Playlist: creates an empty playlist
	//-------------------------------------------------------------------------------------
	public Playlist(){
		// do i need to do super(); ????
		this.songs = new SLL<>();
	}


		//  --> 2. Playlist: creates a playlist with song supplied as the first one
	//-------------------------------------------------------------------------------------
	public Playlist(Song song)
	{
		this.songs = new SLL<>();
		this.songs.addFirst(song);
	}

//---------------------------------------------------------------------------------------------------------------------
	// Methods
//---------------------------------------------------------------------------------------------------------------------

		//  --> 1. getSize: returns the number of songs in currently in the playlist
	//-------------------------------------------------------------------------------------
	public int getSize()
	{
		return songs.getSize();
	}

		//  --> 2. getAt: returns the song at the supplied index
	//-------------------------------------------------------------------------------------
	public Song getAt(int index)
	{
		if(index < 0 || index > this.getSize() - 1  )
		{
			throw new IllegalArgumentException("getAt: Index out of bounds");
		}
		return songs.getAt(index);
	}

		//  --> 3. addSong: adds the song to the end of the playlist
	//-------------------------------------------------------------------------------------
	public void addSong(Song song)
	{
		songs.add(song,this.getSize());
	}

		//  --> 4. totalRunTime: returns total running for all the songs in the playlist
		//		--> Note: throws and exception if the playlist is empty
	//-------------------------------------------------------------------------------------
	public int totalRunTime()
	{
		if(songs.getSize() == 0)
		{
			throw new IllegalStateException("totalRunTime: playlist is empty");
		}

		int totalRunTime =0;
		for (int i =0; i< songs.getSize(); i++)
		{
			totalRunTime += songs.getAt(i).getRunTime();
		}

		return totalRunTime;
	}

		//  --> 5. longestSong: returns the song with the longest running time
		// 		--> Note: throws and exception if the playlist is empty
	//-------------------------------------------------------------------------------------
	public Song longestSong() {
		if(songs.getSize() == 0)
		{
			throw new IllegalStateException("longestSong: playlist is empty");
		}

		Song longestSong = songs.getAt(0);
		for (int i =1; i< songs.getSize(); i++)
		{
			if (longestSong.getRunTime() < songs.getAt(i).getRunTime())
			{
				longestSong = songs.getAt(i);
			}
		}

		return longestSong;
	}

		//  --> 6. toString: returns the playlist
	//-------------------------------------------------------------------------------------
	public String toString()
	{
		return songs.toString();
	}

	
	
	public static void main(String[] args)
    {
    }



}
