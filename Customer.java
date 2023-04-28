import java.sql.SQLException;

public class Customer extends Account {

    private Address address;
    private CreditCard creditCard;


    public Customer(String name, String phone, String email, String password, CreditCard creditCard, Address address) {
        //super.setId(ID);
        super.setName(name);
        super.setEmailAddress(email);
        this.address = address;
        super.setPhoneNumber(phone);
        super.setPassword(password);
        this.creditCard = creditCard;
    }

    public Customer( int id, String name, String phone, String email, String password, CreditCard creditCard, Address address) {
        super.setId(id);
        super.setName(name);
        super.setEmailAddress(email);
        this.address = address;
        super.setPhoneNumber(phone);
        super.setPassword(password);
        this.creditCard = creditCard;
    }

    public void save(DatabaseManager db) throws SQLException {
        db.saveCustomer(this);
    }
    
    public int getID() {
    	return id;
    }
    
    public String getName() { return super.getName(); }

    public String getPhone() { return super.getPhoneNumber(); }
    
    public String getEmail() {
    	return super.getEmailAddress();
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

    public void setPhone(String phoneN) { super.setPhoneNumber(phoneN); }
    
    public void setEmail(String email) {
    	super.setEmailAddress(email);
    }
    
    public void setCreditCardNumber(CreditCard creditCard) {
    	this.creditCard = creditCard;
    }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
