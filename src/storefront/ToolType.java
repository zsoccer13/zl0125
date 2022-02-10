package storefront;

import java.math.BigDecimal;

/**
 * Class that represents a tool type
 * @author zackl
 *
 */
public class ToolType {
	

	private String type;
	private BigDecimal dailyCharge;
	private boolean weekdayCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;
	
	
	/**
	 * @param type
	 */
	public ToolType (String type) {
		this.type = type;
	}
	

	/**
	 * @return the dailyCharge
	 */
	public BigDecimal getDailyCharge() {
		return dailyCharge;
	}


	/**
	 * @param dailyCharge the dailyCharge to set
	 */
	public void setDailyCharge(BigDecimal dailyCharge) {
		this.dailyCharge = dailyCharge;
	}


	/**
	 * @return the weekdayCharge
	 */
	public boolean isWeekdayCharge() {
		return weekdayCharge;
	}


	/**
	 * @param weekdayCharge the weekdayCharge to set
	 */
	public void setWeekdayCharge(boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}


	/**
	 * @return the weekendCharge
	 */
	public boolean isWeekendCharge() {
		return weekendCharge;
	}


	/**
	 * @param weekendCharge the weekendCharge to set
	 */
	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}


	/**
	 * @return the holidayCharge
	 */
	public boolean isHolidayCharge() {
		return holidayCharge;
	}


	/**
	 * @param holidayCharge the holidayCharge to set
	 */
	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


}
