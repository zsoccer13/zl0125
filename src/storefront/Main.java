package storefront;

import java.math.BigDecimal;
import exception.InvalidInputException;

/**
 * Method invoked to begin process of creating a rental agreement.
 * @author zackl
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			String[] rentalInfo = UserPrompt.promptUser();
			RentalAgreementController rentalController = createRental(rentalInfo);
			rentalController.printRental();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	/**
	 * Creates a new rental agreement.
	 * @param rentalInfo the user supplied rental info
	 * @return the rental agreement controller
	 * @throws InvalidInputException
	 */
	public static RentalAgreementController createRental(String[] rentalInfo) {
			
		RentalAgreement rental =  new RentalAgreement(new Tool(rentalInfo[0]), Integer.parseInt(rentalInfo[1]), new BigDecimal(rentalInfo[2]));

		RentalAgreementController rentalController = new RentalAgreementController(rental);
		
		rentalController.setDBInfo();
		
		rentalController.setRentalCheckoutDate(rentalInfo[3]);	
		
		rentalController.chargesCalculation();

		return rentalController;
	}
}