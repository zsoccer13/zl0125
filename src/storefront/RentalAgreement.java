package storefront;

import java.math.BigDecimal;
import java.util.Calendar;


/**
 * The rental agreement object which contains
 * @author zackl
 *
 */
public class RentalAgreement {
		
	private int numberofDays;
	private Calendar checkoutDate;
	private Calendar dueDate;
	private int chargeDays;
	private BigDecimal preDiscountCharge;
	private BigDecimal discountPercentage;
	private BigDecimal discountAmount;
	private BigDecimal finalCharge;
	private Tool tool;
	
	/**
	 * @param tool the tool the customer is renting
	 * @param days the amount of days tool will be rented
	 * @param discount the discount percent
	 */
	public RentalAgreement (Tool tool, int days, BigDecimal discount) {
		this.tool = tool;
		this.numberofDays = days;
		this.discountPercentage = discount;
	}
	

	/**
	 * @return the toolType
	 */
	public Tool getTool() {
		return tool;
	}

	/**
	 * @param toolType the toolType to set
	 */
	public void setTool(Tool tool) {
		this.tool = tool;
	}

	/**
	 * @return the numberofDays
	 */
	public int getNumberofDays() {
		return numberofDays;
	}

	/**
	 * @param numberofDays the numberofDays to set
	 */
	public void setNumberofDays(int numberofDays) {
		this.numberofDays = numberofDays;
	}

	/**
	 * @return the checkoutDate
	 */
	public Calendar getCheckoutDate() {
		return checkoutDate;
	}

	/**
	 * @param checkoutDate the checkoutDate to set
	 */
	public void setCheckoutDate(Calendar checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	/**
	 * @return the dueDate
	 */
	public Calendar getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the chargeDays
	 */
	public int getChargeDays() {
		return chargeDays;
	}

	/**
	 * @param chargeDays the chargeDays to set
	 */
	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}

	/**
	 * @return the preDiscountCharge
	 */
	public BigDecimal getPreDiscountCharge() {
		return preDiscountCharge;
	}

	/**
	 * @param preDiscountCharge the preDiscountCharge to set
	 */
	public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}

	/**
	 * @return the discountPercentage
	 */
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	/**
	 * @param discountPercentage the discountPercentage to set
	 */
	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	/**
	 * @return the discountAmount
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the finalCharge
	 */
	public BigDecimal getFinalCharge() {
		return finalCharge;
	}

	/**
	 * @param finalCharge the finalCharge to set.
	 */
	public void setFinalCharge(BigDecimal finalCharge) {
		this.finalCharge = finalCharge;
	}
	


}
