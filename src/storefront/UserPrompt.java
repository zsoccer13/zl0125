/**
 * 
 */
package storefront;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exception.InvalidInputException;

/**
 * Prompts the user for all of the information needed.  I did not do any validation here as I cover
 * the required things elsewhere for testing.  This would typically be called in a loop if any errors
 * are thrown and caught in order to get the necessary information from the user, but in doing so it
 * would go against specifications, since when some things are input incorrectly, I am told to display a message
 * to the user.
 * @author zackl
 *
 */
public final class UserPrompt {
	
	/**
	 * @return a string list of the user inputs
	 * @throws InvalidInputException 
	 */
	public static String[] promptUser() throws InvalidInputException{
		
		String code = promptCode();
		String days = promptDays();
		String discount = promptDiscount();
		String date = promptDate();
		String[] something = new String[]{code, days, discount, date};
		validateInput(something);
		return new String[]{code, days, discount, date};
		 
	}
	
	/**
	 * @return code the code the user input, converted to upper case
	 */
	public static String promptCode() {
		System.out.print("Tool code: ");

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
 
        String code = null;
		try {
			code = reader.readLine().toUpperCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
	
	/**
	 * @return rentalDays the rental days the user input
	 */
	public static String promptDays() {
		System.out.print("Rental day count: ");

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
 
        String rentalDays = null;
		try {
			rentalDays = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rentalDays;
	}
	
	/**
	 * @return discount the discount percentage the user input
	 */
	public static String promptDiscount() {
		System.out.print("Discount percent: ");

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
 
        String discount = null;
		try {
			discount = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return discount;
	}
	
	/**
	 * @return date the date the user input
	 */
	public static String promptDate() {
		System.out.print("Checkout Date (MM/DD/YY): ");

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
 
        String date = null;
		try {
			date = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	/**
	 * Validates the user's input, I would also validate the tool code and date here but it was not mentioned so I'll consider out of scope.
	 * @param rentalInfo the user supplied rental info
	 * @throws InvalidInputException
	 */
	public static void validateInput(String[] rentalInfo) throws InvalidInputException {
		if (rentalInfo != null) {
			if (rentalInfo.length == 4) {
				if (Integer.parseInt(rentalInfo[1]) < 1) {
					throw new InvalidInputException("Number of rental days must be at least 1");
				}
				int discountRate = Integer.parseInt(rentalInfo[2]);
				if (discountRate <0 || discountRate > 100) {
					throw new InvalidInputException("Discount must be between 0 and 100");
				}
			}
		}
	}

	
}
