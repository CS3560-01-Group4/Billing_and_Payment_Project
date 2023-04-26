import java.sql.*;

public class DatabaseManager {
    private Connection connection;
        
    
    public DatabaseManager(String host, int port, String database, String username, String password) throws SQLException {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public boolean saveCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (name, email, phoneNumb, password) VALUES (?, ?, ?, ?,)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getEmail());
		statement.setString(3, customer.getPhoneNumber());
        statement.setString(4, customer.getPassword());
        statement.executeUpdate();

		sql = "INSERT INTO Address (street, city, zip, state) VALUES (?,?,?,?);";
		statement = connection.prepareStatement(sql);
		Address address = customer.getAddress();
		statement.setString(1, address.getStreetName() + " " + address.getStreetNumber());
		statement.setString(2, address.getCity());
		statement.setString(3, address.getZipCode());
		statement.setString(4, address.getState());
		statement.executeUpdate();

		sql = "INSERT INTO CreditCard (creditCardNumber, csv, expMonth, expDay) VALUES (?,?,?,?);";
		statement = connection.prepareStatement(sql);
		CreditCard card = customer.getCreditCard();
		statement.setString(1, card.getCardNumb());
		statement.setString(2, card.getCsv());
		statement.setString(3, card.getExpMonth());
		statement.setString(4, card.getExpDay());
		statement.executeUpdate();
		return true;
    }

    public void savePurchase(int customerId, Purchase purchase) throws SQLException {
        String sql = "INSERT INTO purchases (customer_id, date, time, total_cost) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, customerId);
        statement.setString(2, purchase.getDate());
        statement.setString(3, purchase.getTime());
        statement.setDouble(4, purchase.getTotalCost());
        statement.executeUpdate();
    }

    public void close() throws SQLException {
        connection.close();
    }
    
   /* public Customer getCustomerCredentials(String user, String pass) {
    	Customer customer = null;
    	try {
    		Statement statement = connection.createStatement();
        	String sql = "select * from Account where username='" + user + "' AND password='" + pass + "'AND accountType='" + "Customer" + "';";
        	ResultSet result = statement.executeQuery(sql);
        	if(result.next()) {
        		int id = result.getInt("accountID");
        		String name = result.getString("username");
        		String email = result.getString("emailAddress");
        		//int creditcard = result.getInt("creditCard");
        		customer = new Customer(id, name, email);
        		return customer;
        	}
    	}catch (Exception e) {
    		System.out.println("Error");
    	}
    	return customer;
    }*/
    
    public boolean searchSalespersonCredentials(String user, String pass) {
    	try {
    		Statement statement = connection.createStatement();
    		String sql = "select * from salesperson where username='" + user + "' AND password='" + pass + "';";
    		ResultSet result = statement.executeQuery(sql);
    		if(result.next()) {
    			return true;
    		}else {
    			return false;
    		}
    	}catch (Exception e) {
    		System.out.println("error");
    		return false;
    	}
    }
    
    public boolean updateCustomerName(int id, String newName) {
    	try {
    		Statement statement = connection.createStatement();
    		String sql = "UPDATE customer SET name ='" + newName + "' WHERE customerID='" + id + "';";
    		statement.executeQuery(sql);
    		return true;
    	}catch (Exception e) {
    		return false;
    	}
    }
    
    public boolean updateEmail(int id, String newEmail) {
    	try {
    		Statement statement = connection.createStatement();
    		String sql = "UPDATE customer SET emailAddress ='" + newEmail + "' WHERE customerID='" + id + "';";
    		statement.executeQuery(sql);
    		return true;
    	}catch (Exception e) {
    		return false;
    	}
    }
}
