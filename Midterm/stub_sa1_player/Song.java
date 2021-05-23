package sa1;

public class Song {
	
	private String name;			//Name of the song
	private String artistFirstName;	//Artist's first name
	private String artistLastName;	//Artist's last name
	private int runTime;			//Run time of the song in seconds
	
	public Song(String name, String artistFirstName, String artistLastName, int runTime) {
		//TODO
	}

	public String getName() {
		return name;
	}

	public String getArtistFirstName() {
		return artistFirstName;
	}

	public String getArtistLastName() {
		return artistLastName;
	}
	
	public int getRunTime() {
		return runTime;
	}
	
	public String toString() {
		return this.name + ", by " + this.artistFirstName + " " + this.artistLastName + ", runtime " + this.runTime + " seconds.";
	}
}
