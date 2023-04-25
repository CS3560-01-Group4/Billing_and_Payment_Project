import java.sql.SQLException;

public class Customer extends Account {
    private int id;
    private String name;
    private String email;
    private int creditCardNumber;

    public Customer(String name, String email, int creditCardNumber) {
        this.name = name;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
    }

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void save(DatabaseManager db) throws SQLException {
        db.saveCustomer(this);
    }
    
    public int getID() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public int getCreditCardNumber() {
    	return creditCardNumber;
    }
    
    public void setID(int id) {
    	this.id = id;
    }
    
    public void setName(String name) {
    	this.Name = name;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void setCreditCardNumber(int creditCardNumber) {
    	this.creditCardNumber = creditCardNumber;
    }
}
