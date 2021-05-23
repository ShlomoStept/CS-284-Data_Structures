package rolodex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class RolodexTest{
	
	@Test
	void first1()
		{
			Rolodex r = new Rolodex ();
			r.addCard("Chad", "23");
			r.addCard("Cris", "3");
			r.addCard("Cris", "4");
			r.addCard("Cris", "5");
			assertEquals(r.contains("Cris"), true);


		}
		@Test
		void second()
		{
			Rolodex r = new Rolodex ();
			r.addCard("Chad", "23");
			r.addCard("Cris", "3");
			r.addCard("Cris", "4");
			r.addCard("Cris", "5");
			assertEquals(r.size(), 4);
		}

		@Test
		void third()
		{
			Rolodex r = new Rolodex ();
			r.addCard("Chad", "23");
			r.addCard("Cris", "3");
			r.addCard("Cris", "4");
			r.addCard("Cris", "5");
			ArrayList<String> s = new ArrayList<String>();
			s.add("23");
			assertEquals(r.lookup("Chad"), s);
		}

		@Test
	void Fourth() {
			Rolodex r = new Rolodex();
			r.addCard("Cris", "4");
			r.addCard("Cris", "5");
			assertThrows(IllegalArgumentException.class, () -> {
				r.addCard("Cris", "5");
				;
			});
			try {
				r.addCard("Cris", "5");
				;
				fail("bad");
			} catch (Exception e) {
				assertTrue(true);
			}
		}

		@Test
		void Fifth()
			{
				Rolodex r = new Rolodex();
				r.addCard("Cris", "4");
				r.addCard("Cris", "5");
				assertThrows(IllegalArgumentException.class, () -> {
					r.removeCard("Sris", "5");
					;
				});
				try {
					r.removeCard("Sris", "5");
					;
					fail("bad");
				} catch (Exception e) {
					assertTrue(true);
				}
			}


		@Test
			void sixth()
			{
				Rolodex r = new Rolodex();
				r.addCard("Cris", "4");
				r.addCard("Cris", "5");
				assertThrows(IllegalArgumentException.class, () -> {
					r.removeCard("Cris", "6");
					;
				});
				try {
					r.removeCard("Cris", "6");
					;
					fail("bad");
				} catch (Exception e) {
					assertTrue(true);
				}
			}

		@Test
		void seventh()
		{
			Rolodex r = new Rolodex();
			r.addCard("Cris", "4");
			r.addCard("Cris", "5");
			assertThrows(IllegalArgumentException.class, () -> {
				r.removeAllCards("Sris");
				;
			});
			try {
				r.removeAllCards("Sris");
				;
				fail("bad");
			} catch (Exception e) {
				assertTrue(true);
			}
		}

		}

