/* 	Shlomo Stept
 * "I pledge my honor that I have abided by the Stevens Honor System"
 *
 *  CS284: Lecture A; Recitation A
 *
 *  Special Assignment 1
 */

package sa1;

public class Song {


//---------------------------------------------------------------------------------------------------------------------
	// Data Fields
//---------------------------------------------------------------------------------------------------------------------

	private String name;			//Name of the song
	private String artistFirstName;	//Artist's first name
	private String artistLastName;	//Artist's last name
	private int runTime;			//Run time of the song in seconds


//---------------------------------------------------------------------------------------------------------------------
	//Constructor
//---------------------------------------------------------------------------------------------------------------------

		//  --> 1. Song: creates a new song
		//     -->  (1) throws an exception if any of the names (song, artist first, or last) is empty
		//     -->  (2) throws an exception if the "runtime" is less than or equal to zero
		//     -->  (3) if no exception is called then the song is created
	//-------------------------------------------------------------------------------------
	public Song(String name, String artistFirstName, String artistLastName, int runTime)
	{
		if(name ==null || artistFirstName ==null || artistLastName ==null)
		{
			throw new IllegalArgumentException("Song: The required information incomplete ");
		}
		if(runTime <= 0)
		{
			throw new IllegalArgumentException("Song: runTime must be greater than zero (0) ");
		}
		this.name = name;
		this.artistFirstName = artistFirstName;
		this.artistLastName = artistLastName;
		this.runTime = runTime;
	}

//---------------------------------------------------------------------------------------------------------------------
	// Methods
//---------------------------------------------------------------------------------------------------------------------

		//  --> 1. getName: returns the name of the song
	//-------------------------------------------------------------------------------------
	public String getName() {
		return name;
	}

		//  --> 2. getArtistFirstName: returns the artist's first name
	//-------------------------------------------------------------------------------------
	public String getArtistFirstName() {
		return artistFirstName;
	}

		//  --> 3. getArtistLastName: returns theArtist's last name
	//-------------------------------------------------------------------------------------
	public String getArtistLastName() {
		return artistLastName;
	}

		//  --> 4. getRunTime: returns the Run time of the song in seconds
	//-------------------------------------------------------------------------------------
	public int getRunTime() {
		return runTime;
	}

		//  --> 5. toString: returns the :: (1) song name (2) artist's first name (3) artist last name (4) song's run-time
	//-------------------------------------------------------------------------------------
	public String toString() {
		return this.name + ", by " + this.artistFirstName + " " + this.artistLastName + ", runtime " + this.runTime + " seconds.";
	}
	
	public static void main(String[] args)
    {
    }
}
