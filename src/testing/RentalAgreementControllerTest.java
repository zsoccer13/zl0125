/**
 * 
 */
package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import exception.InvalidInputException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import storefront.Main;
import storefront.RentalAgreementController;
import storefront.UserPrompt;

/**
 * @author zackl
 *
 */
class RentalAgreementControllerTest {

	/**
	 * Test for invalid discount
	 */
	@Test
	void testInvalidDiscount() {
		InvalidInputException thrown = Assertions.assertThrows(InvalidInputException.class, () -> {
			UserPrompt.validateInput(new String[]{"JAKR", "5", "101", "9/3/15"});
	  });
		Assertions.assertEquals("Discount must be between 0 and 100", thrown.getMessage());
		
	}
	
	/**
	 * Test for Ladder
	 */
	@Test
	void testLadder() throws InvalidInputException {
		RentalAgreementController rentalController = Main.createRental(new String[]{"LADW", "3", "10", "7/2/20"});
		assertEquals(rentalController.getRentalAgreement().getChargeDays(), 2);
		assertEquals(rentalController.getRentalAgreement().getFinalCharge(), new BigDecimal(3.58).setScale(2, RoundingMode.HALF_UP));
	}
	
	/**
	 * Test for Chainsaw
	 * 
	 */
	@Test
	void testChainsaw() throws InvalidInputException {
		RentalAgreementController rentalController = Main.createRental(new String[]{"CHNS", "5", "25", "7/2/15"});
		assertEquals(rentalController.getRentalAgreement().getChargeDays(), 3);
		assertEquals(rentalController.getRentalAgreement().getFinalCharge(), new BigDecimal(3.35).setScale(2, RoundingMode.HALF_UP));
	}
	
	/**
	 * Test for DeWalt Jackhammer Labor day
	 */
	@Test
	void testJackhammerDeWalt() throws InvalidInputException {
		RentalAgreementController rentalController = Main.createRental(new String[]{"JAKD", "6", "0", "9/3/15"});
		assertEquals(rentalController.getRentalAgreement().getChargeDays(), 3);
		assertEquals(rentalController.getRentalAgreement().getFinalCharge(), new BigDecimal(8.97).setScale(2, RoundingMode.HALF_UP));
	}
	
	/**
	 * Test for Ridgid Jackhammer two weekends
	 */
	@Test
	void testJackhammerRigid1() throws InvalidInputException {
		RentalAgreementController rentalController = Main.createRental(new String[]{"JAKR", "9", "0", "7/2/15"});
		assertEquals(rentalController.getRentalAgreement().getChargeDays(), 5);
		assertEquals(rentalController.getRentalAgreement().getFinalCharge(), new BigDecimal(14.95).setScale(2, RoundingMode.HALF_UP));
	}
	
	/**
	 * Test for Ridgid Jackhammer fourth of July
	 */
	@Test
	void testJackhammerRigid2() throws InvalidInputException {
		RentalAgreementController rentalController = Main.createRental(new String[]{"JAKR", "4", "50", "7/2/20"});
		assertEquals(rentalController.getRentalAgreement().getChargeDays(), 1);
		assertEquals(rentalController.getRentalAgreement().getFinalCharge(), new BigDecimal(1.49).setScale(2, RoundingMode.HALF_UP));
			}
	
	/**
	 * Test for invalid rental days
	 */
	@Test
	void testInvalidChargeDays() {
		InvalidInputException thrown = Assertions.assertThrows(InvalidInputException.class, () -> {
			UserPrompt.validateInput(new String[]{"JAKR", "0", "0", "9/3/15"});
	  });
		Assertions.assertEquals("Number of rental days must be at least 1", thrown.getMessage());
		
	}

}
