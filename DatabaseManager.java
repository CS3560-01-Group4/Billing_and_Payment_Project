import java.sql.*;

public class DatabaseManager {
    private Connection connection;
        
    
    public DatabaseManager(String host, int port, String database, String username, String password) throws SQLException {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public void saveCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (name, email, credit_card_number) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getEmail());
        statement.setInt(3, customer.getCreditCardNumber());
        statement.executeUpdate();
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
    
    public boolean searchCustomerCredentials(String user, String pass) {
    	try {
    		Statement statement = connection.createStatement();
        	String sql = "select * from customerAccount where username='" + user + "' AND password='" + pass + "';";
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
}
