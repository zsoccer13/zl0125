package storefront;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * The controller for the rental agreement.
 * @author zackl
 *
 */
public class RentalAgreementController {

	private RentalAgreement rental;
	
	/**
	 *
	 * @param rental
	 */
	public RentalAgreementController(RentalAgreement rental) {
		this.rental = rental;
	}

	
	/**
	 * @return
	 */
	public RentalAgreement getRentalAgreement() {
		return rental;
	}

	/**
	 * @param rental
	 */
	public void setRentalAgreement(RentalAgreement rental) {
		this.rental = rental;
	}
	
	/**
	 * Calls the database to fill the rental.
	 */
	public void setDBInfo () {
		RentalDatabase.getToolInformationFromDB(this.rental);
	}

	/**
	 * Creates a calendar object from a string containing the date.  The date would be validated by this point in a typical setting.
	 * Assumption that the year would be in the 2000 since the user will only enter the last 2 digits.
	 * @param date the string containing the date
	 * @return the calendar object
	 */
	public void setRentalCheckoutDate(String date) {
		
		Calendar calendar = Calendar.getInstance();
		
		String[] dateArray = date.split("/");
		
		calendar.set(2000 + Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[0]) - 1, Integer.parseInt(dateArray[1]));
		
		this.rental.setCheckoutDate(calendar);

	}
	
	/**
	 * Calculates all of the charges for the rental.
	 */
	public void chargesCalculation() {

		chargeDaysCalculation();
		
		this.rental.setPreDiscountCharge(this.rental.getTool().getToolType().getDailyCharge().multiply(BigDecimal.valueOf(this.rental.getChargeDays())));
		
		this.rental.setDiscountAmount(this.rental.getPreDiscountCharge().multiply(this.rental.getDiscountPercentage()).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
		 
		this.rental.setFinalCharge(this.rental.getPreDiscountCharge().subtract(this.rental.getDiscountAmount()));		 
	}
	
	/**
	 * Calculates the number of charge days and sets the due date.
	 */
	private void chargeDaysCalculation() {
		Calendar dueDate = (Calendar) this.rental.getCheckoutDate().clone();
		int chargeDays = 0;
		for (int i = 0; i < this.rental.getNumberofDays(); i++) {
			dueDate.add(Calendar.DATE, 1);
			//since weekend days cannot be holidays
			if (checkWeekend(dueDate)) {
				if (this.rental.getTool().getToolType().isWeekendCharge()) {
					chargeDays++;
					continue;
				} else {
					continue;
				}
			}
			
			if (checkHoliday(dueDate)) {
				if (this.rental.getTool().getToolType().isHolidayCharge()) {
					chargeDays++;
					continue;
				} else {
					continue;
				}
			}
			 if (checkWeekday(dueDate)) {
				 if (this.rental.getTool().getToolType().isWeekdayCharge()) {
					chargeDays++;
					continue;
				 } else {
					 continue;
				 }
			 }
		}

		this.rental.setDueDate(dueDate);
		this.rental.setChargeDays(chargeDays);
	}
	
	/**
	 * Checks if the day is a weekday.
	 * @param cal the calendar
	 * @return boolean True if weekday
	 */
	private boolean checkWeekday(Calendar cal) {
		if (1 < cal.get(Calendar.DAY_OF_WEEK) && cal.get(Calendar.DAY_OF_WEEK) < 7) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the day is a weekend.
	 * @param cal the calendar
	 * @return boolean True if weekend
	 */
	private boolean checkWeekend(Calendar cal) {
		if (1 == cal.get(Calendar.DAY_OF_WEEK) || cal.get(Calendar.DAY_OF_WEEK) == 7) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the day is a holiday.
	 * @param cal the calendar
	 * @return boolean True if holiday
	 */
	private boolean checkHoliday(Calendar cal) {
		return checkIndependenceDay(cal) || checkLaborDay(cal);
	}
	
	/**
	 * Checks if the day is a Independence Day.
	 * @param cal the calendar
	 * @return boolean True if Independence Day
	 */
	private boolean checkIndependenceDay(Calendar cal) {
		if (cal.get(Calendar.MONTH) == 6) {
			if (cal.get(Calendar.DAY_OF_MONTH) == 4 ) {
				//Weekend days which fall on the 4th are not counted since it is observed on the weekday
				if (1 < cal.get(Calendar.DAY_OF_WEEK) && cal.get(Calendar.DAY_OF_WEEK) < 7) {
					return true;
				}
			}
			if (cal.get(Calendar.DAY_OF_MONTH) == 3 ) {
				if (cal.get(Calendar.DAY_OF_WEEK) == 6) {
					return true;
				}
			}
			if (cal.get(Calendar.DAY_OF_MONTH) == 5 ) {
				if (cal.get(Calendar.DAY_OF_WEEK) == 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if the day is Labor day.
	 * @param cal the calendar
	 * @return boolean True if Labor Day
	 */
	private boolean checkLaborDay(Calendar cal) {
		if (cal.get(Calendar.MONTH) == 8) {
			if (cal.get(Calendar.DAY_OF_WEEK) == 2) {
				if (cal.get(Calendar.DAY_OF_MONTH) <= 7 ) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Prints out all of the information for the rental agreement.
	 */
	public void printRental() {
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
		
		System.out.println("\nRENTAL AGREEMENT\n");
		System.out.println("Tool code: " + this.rental.getTool().getCode());
		System.out.println("Tool type: " + this.rental.getTool().getToolType().getType());
		System.out.println("Tool brand: " + this.rental.getTool().getBrand());
		System.out.println("Rental days: " + this.rental.getNumberofDays());
		int checkoutYear = this.rental.getCheckoutDate().get(Calendar.YEAR)-2000;
		System.out.println("Checkout date: " + this.rental.getCheckoutDate().get(Calendar.MONTH) + "/" + this.rental.getCheckoutDate().get(Calendar.DATE) + "/" + checkoutYear);
		int dueYear = this.rental.getDueDate().get(Calendar.YEAR)-2000;
		System.out.println("Due date: " + this.rental.getDueDate().get(Calendar.MONTH) + "/" + this.rental.getDueDate().get(Calendar.DATE) + "/" + dueYear);
		System.out.println("Daily rental charge: " + dollarFormat.format(this.rental.getTool().getToolType().getDailyCharge()));
		System.out.println("Charge days: " + this.rental.getChargeDays());
		System.out.println("Pre-discount charge: " + dollarFormat.format(this.rental.getPreDiscountCharge()));
		System.out.println("Discount percent: " + this.rental.getDiscountPercentage() + "%");
		System.out.println("Discount amount: " + dollarFormat.format(this.rental.getDiscountAmount()));
		System.out.println("Final charge: " + dollarFormat.format(this.rental.getFinalCharge()));		
	}

	
	
}
