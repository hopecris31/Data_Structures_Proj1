package proj1;
/**
 * JUnit test class.  Use these tests as models for your own.
 *
 * @author Hope Crisafi
 * @version Updated 11/21/2022
 */
import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import proj1.Coinbank;

public class CoinbankTest {

	@Rule // a test will fail if it takes longer than 1/10 of a second to run
	public Timeout timeout = Timeout.millis(100);


	/**
	 * Sets up a bank with the given coins
	 * @param pennies number of pennies you want
	 * @param nickels number of nickels you want
	 * @param dimes number of dimes you want
	 * @param quarters number of quarters you want
	 * @return the Coinbank filled with the requested coins of each type
	 */
	private Coinbank makeBank(int pennies, int nickels, int dimes, int quarters) {
		Coinbank c = new Coinbank();
		int[] money = new int[]{pennies, nickels, dimes, quarters};
		int[] denom = new int[]{1,5,10,25};
		for (int index=0; index<money.length; index++) {
			int numCoins = money[index];
			for (int coin=0; coin<numCoins; coin++) {
				c.insert(denom[index]);
			}
		}
		return c;
	}

	/**
	 * CONSTRUCTOR TEST
	 */

	@Test // tests constructor; bank should be empty
	public void testConstructor() {
		Coinbank emptyDefault = new Coinbank();
		assertEquals(0, emptyDefault.get(1));
		assertEquals(0, emptyDefault.get(5));
		assertEquals(0, emptyDefault.get(10));
		assertEquals(0, emptyDefault.get(25));
		assertEquals("The bank currently holds $0.00 consisting of \n" +
				"0 pennies\n" +
				"0 nickels\n" +
				"0 dimes\n" +
				"0 quarters\n", emptyDefault.toString());
	}

	/**
	 * __________
	 * GET TESTS
	 * __________
	 */

	@Test // tests get; should return quantity of coin in the bank
	public void testGet() {
		Coinbank c = makeBank(0,2,15,1);
		assertEquals(0,c.get(1));
		assertEquals(2,c.get(5));
		assertEquals(15,c.get(10));
		assertEquals(1,c.get(25));
	}

	@Test // tests get; tries to get a coin with invalid denomination.
	public void testGetInvalidCoin() {
		Coinbank c = makeBank(0,0,0,0);
		assertEquals(-1, c.get(4));
	}

	@Test // tests get; should not alter the contents of the bank
	public void testGetDoesNotAlterContents() {
		Coinbank c = makeBank(0,2,15,1);
		c.get(1);
		c.get(5);
		c.get(10);
		c.get(25);
		String expected = "The bank currently holds $1.85 consisting of \n0 pennies\n2 nickels\n15 dimes\n1 quarters\n";
		assertEquals(expected,c.toString());
	}

	@Test // tests the bank status after calling get
	public void testBankStatusGet() {
		Coinbank c = makeBank(2,3,4,5);

		assertEquals(2, c.get(1));
	}

	/**
	 * _____________
	 * INSERT TESTS
	 * _____________
	 */

	@Test // tests insert; inserting a penny into the bank.
	public void testInsert() {
		Coinbank c = makeBank(0,0,0,0);
		assertEquals(true, c.insert(1));
	}

	@Test // tests insert; inserting a nickel should return true and one nickel should be in bank
	public void testInsertNickel() {
		Coinbank c = new Coinbank();
		assertTrue(c.insert(5));
		assertEquals(1,c.get(5));
	}

	@Test // tests insert; inserting an invalid coin type into the bank.
	public void testInsertInvalidCoin() {
		Coinbank c = makeBank(0,0,0,0);
		assertEquals(false, c.insert(4));
	}

	@Test // tests the bank status after calling insert
	public void testBankStatusInsert() {
		Coinbank c = makeBank(0,0,0,0);

		assertTrue(c.insert(10));
		assertEquals(1, c.get(10));
	}

	/**
	 * _____________
	 * REMOVE TESTS
	 * ____________
	 */

	@Test // tests remove; removing the exact number of a type of coin that is present (total left of that type is 0)
	public void testRemoveExactNumberCoins() {
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(5,c.remove(25,5));
		String expected = "The bank currently holds $0.39 consisting of \n4 pennies\n1 nickels\n3 dimes\n0 quarters\n";
		assertEquals(expected,c.toString());
	}

	@Test // tests remove; remove should not do anything if a 3-cent (invalid) coin is requested.
	public void testRemoveInvalidCoin() {
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(0,c.remove(3,1));
	}

	@Test // tests remove; 5 quarters - 3 should have 3 removed from bank.
	public void testRemoveRemainingCoins() {
		Coinbank c = makeBank(4,1,3,5);
		assertEquals(3, c.remove(25, 3));
	}

	@Test // tests remove; tries to remove 4 coins but the bank has none.
	public void testRemoveNoCoins() {
		Coinbank c = makeBank(0,0,0,0);
		assertEquals(0, c.remove(1,4));
	}

	@Test // tests remove; tries to remove 4 coins but the bank only has 3. Should remove all remaining coins
	public void testRemoveNotEnoughCoins() {
		Coinbank c = makeBank(3,0,0,0);
		assertEquals(3, c.remove(1,4));
	}

	@Test // tests remove; tries to remove a negative number
	public void testRemoveInvalidNumber() {
		Coinbank c = makeBank(0,0,0,0);
		assertEquals(0, c.remove(1, -4));
	}

	@Test // tests the bank status after calling insert
	public void testBankStatusRemove() {
		Coinbank c = makeBank(2,3,4,5);

		assertEquals(3, c.remove(10, 3));
		assertEquals(1, c.get(10));
	}


	/**
	 * ________________
	 * TO_STRING TESTS
	 * _______________
	 */

	@Test // tests toString; bank contains coins
	public void tesTtoString() {
		Coinbank c = makeBank(2,3,4,5);
		System.out.println(c.toString());

		assertEquals("The bank currently holds $1.82 consisting of \n" +
				"2 pennies\n" +
				"3 nickels\n" +
				"4 dimes\n" +
				"5 quarters\n", c.toString());
	}

	@Test // tests toString; bank is empty
	public void testToStringEmpty() {
		Coinbank c = makeBank(0,0,0,0);

		assertEquals("The bank currently holds $0.00 consisting of \n" +
				"0 pennies\n" +
				"0 nickels\n" +
				"0 dimes\n" +
				"0 quarters\n", c.toString());
	}

}
