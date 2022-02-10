package storefront;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * This is a mock up of the database class that would exist in a business setting.
 * @author zackl
 *
 */
public final class RentalDatabase {
	

	/**
	 * This is where the tool database call would be done to get all of the information associated with the tool.
	 * @param rental
	 */
	public static void getToolInformationFromDB(RentalAgreement rental) {
		
		HashMap<String, String[]> toolCodeMap = createHashMapToolCodeInfo();
		
		String[] toolCodeInfo = toolCodeMap.get(rental.getTool().getCode());
		
		Tool tool = rental.getTool();
		ToolType toolType = new ToolType(toolCodeInfo[0]);
		tool.setToolType(toolType);
		tool.setBrand(toolCodeInfo[1]);
		
		getToolTypeInformationFromDB(toolType);
	}
	
	/**
	 * This is where the tool type database call would be done to get all of the information associated with the tool type.
	 * @param toolType
	 */
	public static void getToolTypeInformationFromDB(ToolType toolType) { 
		
		HashMap<String, String[]> toolTypeMap = createHashMapToolTypeInfo();
		
		String[] chargeInfo = toolTypeMap.get(toolType.getType());
		toolType.setDailyCharge(new BigDecimal(chargeInfo[0]));
		toolType.setWeekdayCharge(convertYesNoToBoolean(chargeInfo[1]));
		toolType.setWeekendCharge(convertYesNoToBoolean(chargeInfo[2]));
		toolType.setHolidayCharge(convertYesNoToBoolean(chargeInfo[3]));
	}
	
	/**
	 * This is a mock up of the tool database that would exist in a business setting.
	 * @return toolCodes map of the tool codes
	 */
	private static HashMap<String, String[]> createHashMapToolCodeInfo() {
		HashMap<String, String[]> toolCodes = new HashMap<String, String[]>();
		toolCodes.put("CHNS", new String[]{"Chainsaw", "Stihl"});
		toolCodes.put("LADW", new String[]{"Ladder", "Werner"});
		toolCodes.put("JAKD", new String[]{"Jackhammer", "Dewalt"});
		toolCodes.put("JAKR", new String[]{"Jackhammer", "Ridgid"});
		return toolCodes;
	}
	
	/**
	 * This is a mock up of the tool type database that would exist in a business setting.
	 * @return tooltypeInfo map of the tool type info
	 */
	private static HashMap<String, String[]> createHashMapToolTypeInfo() {
		HashMap<String, String[]> tooltypeInfo = new HashMap<String, String[]>();
		tooltypeInfo.put("Ladder", new String[]{"1.99", "Yes", "Yes", "No"});
		tooltypeInfo.put("Chainsaw", new String[]{"1.49", "Yes", "No", "Yes"});
		tooltypeInfo.put("Jackhammer", new String[]{"2.99", "Yes", "No", "No"});
		return tooltypeInfo;
	}
	
	/**
	 * Converts the Yes or No from the database into a boolean.
	 * @param stringToConvert
	 * @return boolean
	 */
	private static boolean convertYesNoToBoolean(String stringToConvert) {
		if (stringToConvert == "Yes") {
			return true;
		}
		return false;
	}
}
