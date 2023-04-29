import java.sql.*;

public class DatabaseManager {
    private Connection connection;
        
    
    public DatabaseManager() throws SQLException {
		String host = "containers-us-west-34.railway.app";
		int port = 5939;
		String database = "gymmembership";
		String username = "root";
		String password = "91laqZk1CB5VM13WltEE";

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        this.connection = DriverManager.getConnection(url, username, password);
    }

    //given an input Customer, save it to the database
	public boolean saveCustomer(Customer customer) throws SQLException {
		//save Account to database
		String sql = "INSERT INTO Account (name, phoneNumber, emailAddress, password) VALUES (?,?,?,?);";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, customer.getName());
		statement.setString(2, customer.getPhoneNumber());
		statement.setString(3, customer.getEmail());
		statement.setString(4, customer.getPassword());
		statement.executeUpdate();

		int accountID = 0;
		ResultSet rs = statement.getGeneratedKeys();
		if(rs.next()) {
			accountID = rs.getInt(1);
		}

		//save Customer
		sql = "INSERT INTO Customer (Account_accountID) VALUES (?);";
		PreparedStatement statement4 = connection.prepareStatement(sql);
		statement4.setInt(1, accountID);
		statement4.executeUpdate();

		//save Address
		sql = "INSERT INTO Address (streetName, city, zipcode, state,Customer_Account_accountID) VALUES (?,?,?,?,?);";
		PreparedStatement statement2 = connection.prepareStatement(sql);
		statement2.setString(1, customer.getAddress().getStreetName());
		statement2.setString(2, customer.getAddress().getCity());
		statement2.setString(3, customer.getAddress().getZipCode());
		statement2.setString(4, customer.getAddress().getState());
		statement2.setInt(5, accountID);
		statement2.executeUpdate();

		//save CreditCard
		sql = "INSERT INTO CreditCard (creditCardNumb, csv, expMonth, expDay, Customer_Account_accountID) VALUES (?,?,?,?,?);";
		PreparedStatement statement3 = connection.prepareStatement(sql);
		statement3.setString(1, customer.getCreditCard().getCardNumb());
		statement3.setString(2, customer.getCreditCard().getCsv());
		statement3.setString(3, customer.getCreditCard().getExpMonth());
		statement3.setString(4, customer.getCreditCard().getExpDay());
		statement3.setInt(5, accountID);
		statement3.executeUpdate();

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
    
    public Customer getCustomerCredentials(String email, String pass) {
		Customer customer = null;
		int id;
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from Account where emailAddress='" + email + "' AND password='" + pass + "';";
			ResultSet result = statement.executeQuery(sql);
			if(result.next()) {
				String name = result.getString("name");
				String phone = result.getString("phoneNumber");
				id = result.getInt("accountID");

				//get info about customer by searching Address table
				sql = "select * from Address where Customer_Account_accountID='" + id + "';";
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				Address address = null;
				if(rs.next()) {
					String streetName = rs.getString("streetName");
					String city = rs.getString("city");
					int zip = rs.getInt("zipcode");
					String state = rs.getString("state");
					address = new Address(streetName,city, Integer.toString(zip),state);
				}

				//get info about customer by searching Address table
				sql = "select * from CreditCard where Customer_Account_accountID='" + id + "';";
				statement = connection.createStatement();
				ResultSet r = statement.executeQuery(sql);
				CreditCard creditCard = null;
				if(r.next()) {
					String creditNumb = r.getString("creditCardNumb");
					String csv = r.getString("csv");
					String expMonth = r.getString("expMonth");
					String expDay = r.getString("expDay");
					creditCard = new CreditCard(creditNumb,csv,expMonth,expDay);
				}

				return new Customer(id, name, phone, email, pass, creditCard, address);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return customer;
    }
    
    public boolean searchSalespersonCredentials(String user, String pass) {

		String sql = "SELECT * " +
				"FROM Account " +
				"INNER JOIN Salesperson " +
				"ON Account.accountID = Salesperson.Account_accountID " +
				"WHERE password =? " +
				"AND name =? ";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, pass);
			statement.setString(2, user);

			ResultSet result = statement.executeQuery();

			if(result.next()) {

				return true;
			}else {
				System.out.println("no result");
				return false;
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
    }
    
    public boolean updateCustomerName(int id, String newName) {
		System.out.println(id);
		try {
			Statement statement = connection.createStatement();
			String sql = "UPDATE Account SET name='" + newName + "' WHERE accountID='" + id + "';";
			statement.executeUpdate(sql);
			return true;
		}
        catch (SQLException e) {
			System.out.println(e);
			return false;
		}
    }
    
    public boolean updateEmail(int id, String newEmail) {
		try {
			Statement statement = connection.createStatement();
			String sql = "UPDATE Account SET emailAddress ='" + newEmail + "' WHERE accountID=" + 1;
			statement.executeUpdate(sql);
			return true;
		}catch (Exception e) {
			System.out.print(e);
			return false;
		}
    }

	public boolean searchAdmin(String email, String pass) {
		boolean verified = false;
		String sql = "select * from Account join systemadmin " +
				"on Account.accountID = systemadmin.Account_accountID " +
				"where  emailAddress=? and password=? and systemadmin.Account_accountID=11;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, pass);

			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				verified = true;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return verified;
	}
	public void editSubscription() {
		;
	}
}
