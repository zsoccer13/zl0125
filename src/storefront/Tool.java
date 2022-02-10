package storefront;

/**
 * Class that represents a single tool
 * @author zackl
 *
 */
public class Tool {


	public String code;
	private ToolType type;
	private String brand;
	
	
	/**
	 * @param code
	 */
	public Tool (String code) {
		this.code = code;
	}
	
	
	/**
	 * @return the type
	 */
	public ToolType getToolType() {
		return type;
	}


	/**
	 * @param type
	 */
	public void setToolType(ToolType type) {
		this.type = type;
	}


	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}


	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

}
