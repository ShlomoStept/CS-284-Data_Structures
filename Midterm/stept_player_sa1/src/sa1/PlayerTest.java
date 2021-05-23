package sa1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	// clear the queue and then try to play the next song should get a exception for the No song is currently being played not a null pointer
	@Test
	void first1()
		{
			Song s1 = new Song("Highway to Hell", "AC /", "DC", 207);
			Song s2 = new Song("Stairway to Heaven", "Led", "Zepplin", 482);
			Playlist p = new Playlist ();
			p.addSong(s1);
			p.addSong(s2);
			Player y = new Player(p);
			y.clearQueue();
			assertThrows(IllegalStateException.class, () -> {
				y.playNextSong();
				;
			});
			try {
				y.playNextSong();
				;
				fail("bad");
			} catch (Exception e) {
				assertTrue(true);
			}


		}
	
		//Create an empty player and make sure remaining time throws an error
		@Test
		void second()
		{
			Player y = new Player();
			assertThrows(IllegalStateException.class, () -> {
				y.getRemainingRunTime();
				;
			});
			try {
				y.getRemainingRunTime();
				;
				fail("bad");
			} catch (Exception e) {
				assertTrue(true);
			}
		}

		
		//Create a 1 song player and make sure remaining time is zero 
		@Test
		void third()
		{
			Song s1 = new Song("Highway to Hell", "AC /", "DC", 207);
			Playlist p = new Playlist ();
			p.addSong(s1);
			Player y = new Player(p);
			assertEquals(y.getRemainingRunTime(), 0);
		}
		
		

		//Create an empty player and make sure get-all-songs throws an error
		@Test
		void Fourth() {
			Player y = new Player();
			assertThrows(IllegalStateException.class, () -> {
				y.getAllSongs();
				;
			});
			try {
				y.getAllSongs();
				;
				fail("bad");
			} catch (Exception e) {
				assertTrue(true);
			}
		}

		//Create an empty player and make sure getCurentsong throws an error
		@Test
		void Fifth()
			{
				Player y = new Player();
				assertThrows(IllegalArgumentException.class, () -> {
					y.getCurrentSong();
					;
				});
				try {
					y.getCurrentSong();
					;
					fail("bad");
				} catch (Exception e) {
					assertTrue(true);
				}
			}


		// Create an empty player add a song and make sure it updates the totalruntime
		@Test
			void sixth()
			{
			Song s1 = new Song("Highway to Hell", "AC /", "DC", 207);
			Player y = new Player();
			y.addSong(s1);
			assertEquals(y.getTotalRuntime(), 207);
			}

		
		
		// Add a few songs play all and clear it and make sure run time is correct
			@Test
				void seventh()
				{
				Song s1 = new Song("Highway to Hell", "AC /", "DC", 207);
				Song s2 = new Song("Stairway to Heaven", "Led", "Zepplin", 482);
				Playlist p = new Playlist ();
				p.addSong(s1);
				p.addSong(s2);
				Player y = new Player(p);
				y.playNextSong();
				y.clearQueue();
				assertEquals(y.getTotalRuntime(), 689);
				}
				
		// Add a few songs and make sire the queue size adds properly and takes off properly while using playnextSong()
			@Test
			void eigth()
			{
				Song s1 = new Song("Highway to Hell", "AC /", "DC", 207);
				Song s2 = new Song("Stairway to Heaven", "Led", "Zepplin", 482);
				Player y = new Player();
				y.addSong(s1);
				y.addSong(s2);
				y.playNextSong();
				y.addSong(s1);
				y.playNextSong();
				
				assertEquals(y.getPlayQueueSize(), 1);
			}
			
			// Add a few songs and clear it and make sure it has the proper count for songs in play queue
				@Test
				void Ninth()
				{
					Song s1 = new Song("Highway to Hell", "AC /", "DC", 207);
					Song s2 = new Song("Stairway to Heaven", "Led", "Zepplin", 482);
					Playlist p = new Playlist ();
					p.addSong(s1);
					p.addSong(s2);
					Player y = new Player(p);
					y.clearQueue();
					assertEquals(y.getPlayQueueSize(), 0);
				}
		
		// make sure longest song is working properly 		
		@Test
		void tenth()
		{
			Song s1 = new Song("Highway to Hell", "AC /", "DC", 207);
			Song s2 = new Song("Stairway to Heaven", "Led", "Zepplin", 482);
		
			Playlist p = new Playlist ();
			p.addSong(s1);
			p.addSong(s2);
			assertEquals(p.longestSong(), s2);
	}

		// make sure that when you add a song to the playlist it gets put in the right spot 
		@Test
		void Eleventh()
		{
			Song s1 = new Song("Highway to Hell", "AC /", "DC", 207);
			Song s2 = new Song("Stairway to Heaven", "Led", "Zepplin", 482);
			Playlist p = new Playlist ();
			p.addSong(s1);
			p.addSong(s2);
			assertEquals(p.getAt(1), s2);
	}
		// make sure total running time is working correctly 
				@Test
				void twelth()
				{
					Song s1 = new Song("Highway to Hell", "AC /", "DC", 207);
					Song s2 = new Song("Stairway to Heaven", "Led", "Zepplin", 482);
					Playlist p = new Playlist ();
					p.addSong(s1);
					p.addSong(s2);
					assertEquals(p.totalRunTime(), 689);
			}
}
