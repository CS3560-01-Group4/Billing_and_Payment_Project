import java.sql.SQLException;

public class Customer extends Account {
    private int id;
    private String name;
    private String email;
    private Address address;
    private String phone;
    private String password;
    private CreditCard creditCard;


    public Customer(String name, String email, Address address, String phone, String password, CreditCard creditCard) {
        //this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.creditCard = creditCard;
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
    
    public CreditCard getCreditCard() {
    	return creditCard;
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
    
    public void setCreditCardNumber(CreditCard creditCard) {
    	this.creditCard = creditCard;
    }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
