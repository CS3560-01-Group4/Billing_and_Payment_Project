import java.sql.SQLException;

public class Purchase {
    private int id;
    private int customerId;
    private String date;
    private String time;
    private double totalCost;

    public Purchase(int customerId, String date, String time, double totalCost) {
        this.customerId = customerId;
        this.date = date;
        this.time = time;
        this.totalCost = totalCost;
    }

    public void save(DatabaseManager db) throws SQLException {
        db.savePurchase(this.customerId, this);
    }
    
    public int getID() {
    	return id;
    }
    
    public int getCustomerID() {
    	return customerId;
    }
    
    public String getDate() {
    	return date;
    }
    
    public String getTime() {
    	return time;
    }
    
    public double getTotalCost() {
    	return totalCost;
    }
    
    public void setID(int id) {
    	this.id = id;
    }
    
    public void setCustomerID(int customerId) {
    	this.customerId = customerId;
    }
    
    public void setDate(String date) {
    	this.date = date;
    }
    
    public void setTime(String time) {
    	this.time = time;
    }
    
    public void setTotalCost(double totalCost) {
    	this.totalCost = totalCost;
    }
    
    
}
