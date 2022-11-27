package proj1;
import java.util.Random;

/**
 * Driver for the Coinbank Project
 *
 * @author Hope Crisafi
 * @version Revision: 11/21/2022
 */

public class Client {
	public static void main(String[] args) {
		Coinbank bank = new Coinbank();

		for (int i=0; i<10; i++) {
			bank.insert(getRandomCoin());
		}
		System.out.println(bank);

		int toRemove = (int)(Math.random() * 6);
		int denom = getRandomCoin();
		int got = bank.remove(denom,toRemove);
		System.out.println("\nTried to remove " + toRemove + " " + denom + "-cent coins and got " + got + "\n");
		System.out.println(bank);
	}

	/**
	 * Gets a random coin denomination
	 * @return 1, 5, 10, or 25
	 */
	public static int getRandomCoin() {
		Random r = new Random();
		int num = r.nextInt(4);
		switch(num) {
			case 0: return Coinbank.PENNY_VALUE;
			case 1: return Coinbank.NICKEL_VALUE;
			case 2: return Coinbank.DIME_VALUE;
			case 3: return Coinbank.QUARTER_VALUE;
			default: return 0;
		}
	}

}
