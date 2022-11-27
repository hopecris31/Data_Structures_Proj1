package proj1;

/**
 * Coinbank class.
 *
 * Represents a coinbank, where the user puts a coin into the bank, and the coin would
 * roll and fall into the hole in which the size of the coin would permit.  This would
 * result with each coin in a sorted stack with its own type.
 *
 * @author Hope Crisafi
 * @version Revision: 11/21/2022
 */
public class Coinbank {
	public static final int INVALID_NUM = -1;
	public static final int PENNY_VALUE = 1;
	public static final int NICKEL_VALUE = 5;
	public static final int DIME_VALUE = 10;
	public static final int QUARTER_VALUE = 25;

	private final int NO_INDEX = -1;
	private final int PENNY_INDEX = 0;
	private final int NICKEL_INDEX = 1;
	private final int DIME_INDEX = 2;
	private final int QUARTER_INDEX = 3;

	private final int COINTYPES = 4;
	private final int EMPTY = 0;

	private int[] holder;

	/**
	 * Default constructor
	 */
	public Coinbank() {
		this.holder = new int[COINTYPES];
		for(int i=0; i<holder.length; i++){
			this.holder[i] = EMPTY;
		}
	}

	/**
	 * getter; gets the number of coins in the bank of that type
	 * @param coinType denomination of coin to get. Valid denominations are 1,5,10,25
	 * @return number of coins that bank is holding of that type, or -1 if denomination not valid
	 */
	public int get(int coinType){
		if(isBankable(coinType)) {
			return this.holder[getCoinIndex(coinType)];
		}
		else{
			return INVALID_NUM;
		}
	}



	/**
	 * insert valid coin into bank.  Returns true if deposit
	 * successful (i.e. coin was penny, nickel, dime, or quarter).
	 * Returns false if coin not recognized (can only insert 1 at a time)
	 *
	 * @param coinType either 1, 5, 10, or 25 to be valid
	 * @return true if deposit successful, else false
	 */
	public boolean insert(int coinType){
		if (!isBankable(coinType)) {
			return false;
		}
		else {
			set(coinType, get(coinType)+1);
			return true;
		}
	}

	/**
	 * returns the requested number of the requested coin type, if possible.
	 * Does nothing if the coin type is invalid.  If bank holds
	 * fewer coins than is requested, then all of the coins of that
	 * type will be returned.
	 * @param coinType either 1, 5, 10, or 25 to be valid
	 * @param requestedCoins number of coins to be removed
	 * @return number of coins that are actually removed
	 */
	public int remove(int coinType, int requestedCoins) {
		int removedCoins = 0;
		if (isBankable(coinType) && requestedCoins >= 0) {
			removedCoins = findCoinsRemoved(coinType, requestedCoins);
		}
		return removedCoins;
	}


	/**
	 * Returns bank as a printable string
	 */
	public String toString() {

		double total = (get(PENNY_VALUE) * PENNY_VALUE +
				get(NICKEL_VALUE) * NICKEL_VALUE +
				get(DIME_VALUE) * DIME_VALUE +
				get(QUARTER_VALUE) * QUARTER_VALUE) / 100.00;

		String toReturn = "The bank currently holds $" + String.format("%.2f", total) + " consisting of \n";
		toReturn+=get(PENNY_VALUE) + " pennies\n";
		toReturn+=get(NICKEL_VALUE) + " nickels\n";
		toReturn+=get(DIME_VALUE) + " dimes\n";
		toReturn+=get(QUARTER_VALUE) + " quarters\n";
		return toReturn;
	}


	/** ------------------------------
	 *      PRIVATE HELPER METHODS
	 * _______________________________
	 */


	/**
	 * determines the index of the coin type
	 * @param coinType the type of coin, 1, 5, 10, 25 are valid inputs
	 * @return the index of the coin type in the holder
	 */
	private int getCoinIndex(int coinType){
		switch (coinType) {
			case PENNY_VALUE:
				return PENNY_INDEX;
			case NICKEL_VALUE:
				return NICKEL_INDEX;
			case DIME_VALUE:
				return DIME_INDEX;
			case QUARTER_VALUE:
				return QUARTER_INDEX;
			default:
				return NO_INDEX;
		}
	}


	/**
	 * setter; sets the number of coins in the bank to a given number
	 * @param coinType denomination of coin to set
	 * @param numCoins number of coins
	 */
	private void set(int coinType, int numCoins) {
		if (isBankable(coinType))
			this.holder[getCoinIndex(coinType)] = numCoins;
	}


	/**
	 * Return true if given coin can be held by this bank.  Else false.
	 * @param coin penny, nickel, dime, or quarter is bankable.  All others are not.
	 * @return true if bank can hold this coin, else false
	 */
	private boolean isBankable(int coin){
		switch (coin) {
			case PENNY_VALUE: case NICKEL_VALUE:
			case DIME_VALUE: case QUARTER_VALUE:
				return true;
			default:
				return false;
		}
	}


	/**
	 * helper, finds and returns the number of coins left after taking them out of holder
	 * @param coinType the type of coin to be removed
	 * @param requestedCoins the number of coins to be taken out
	 * @return the number of coins left after subtraction
	 */
	private int findCoinsRemoved (int coinType, int requestedCoins){
		int coinsWeHave = get(coinType);
		int coinsLeft = numLeft(requestedCoins, coinsWeHave);
		this.set(coinType, coinsLeft);
		return coinsWeHave - coinsLeft;
	}


	/**
	 * returns number of coins remaining after removing the
	 * requested amount.  Returns zero if requested amount > what we have
	 * @param numWant number of coins to be removed
	 * @param numHave number of coins you have
	 * @return number of coins left after removal
	 */
	private int numLeft(int numWant, int numHave){
		return Math.max(0, numHave-numWant);
	}

}
