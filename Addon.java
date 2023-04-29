/**
 * 
 */

/**
 * @author Cristofer Vargas , , , 
 *
 */
abstract class Addon {

	private int addonID; //key identifier
	private String name;
	private double price;
	
	public int getAddonID() {
		return addonID;
	}
	public void setAddonID(int addonID) {
		this.addonID = addonID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
