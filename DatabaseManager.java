import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
			String sql = "UPDATE Account SET emailAddress ='" + newEmail + "' WHERE accountID='" + id + "';";
			statement.executeUpdate(sql);
			return true;
		}catch (Exception e) {
			System.out.print(e);
			return false;
		}
    }

	public boolean updatePassword(int id, String newPassword){
		try {
			Statement statement = connection.createStatement();
			String sql = "UPDATE Account SET password ='" + newPassword + "' WHERE accountID='" + id + "';";
			statement.executeUpdate(sql);
			return true;
		}catch (Exception e) {
			System.out.print(e);
			return false;
		}

	}

	//search database for Admin account, returns true if Admin exists for the given email and password
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

	//update subscription for customer (the membership only, not any potential addons)
	public void editSubscription(int memberID, String newMembership) {
		//calculate renewal date
		String purchaseDate = LocalDate.now().toString();
		int year = Integer.parseInt(purchaseDate.substring(0,4));
		int month = Integer.parseInt(purchaseDate.substring(5,7));
		int day = Integer.parseInt(purchaseDate.substring(8));
		String renewalStr;

		if(newMembership.equals("weekly")) {
			renewalStr = java.time.LocalDate.of(year,month,day).plusWeeks(1).toString();
		}else if(newMembership.equals("monthly")) {
			renewalStr = java.time.LocalDate.of(year,month,day).plusMonths(6).toString();
		}else{//yearly
			renewalStr = java.time.LocalDate.of(year,month,day).plusMonths(12).toString();
		}
		System.out.println(renewalStr);


		String sql = "update OwnedMembership set Membership_name=?," +
				"startDate=?, renewalDate=?, status=? where memberID=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newMembership);
			statement.setString(2, purchaseDate);
			statement.setString(3, renewalStr);
			statement.setString(4, "Active");
			statement.setInt(5, memberID);
			statement.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	// returns an array of Classes from the class table
	public Class[] getClasses() {
		// query number of rows
		String row =
				"SELECT COUNT(*) AS numOfRows FROM (" +
						"SELECT addonID, name, classDate, timeSlot, instructorName, classLength, price" +
						"FROM Addon" +
						"INNER JOIN Class" +
						"ON Addon.addonID = Class.Addon.addonID" +
						"GROUP BY Addon.addonID" +
						") t";

		String sql =
				"SELECT addonID, name, classDate, timeSlot, instructorName, classLength, price" +
						"FROM Addon" +
						"INNER JOIN Class" +
						"ON Addon.addonID = Class.Addon_addonID";

		Class[] classes;

		try {
			PreparedStatement statement = connection.prepareStatement(row);
			ResultSet result = statement.executeQuery();

			int rowCount = result.getInt("numOfRows");
			classes = new Class[rowCount];

			statement = connection.prepareStatement(sql);
			result = statement.executeQuery();

			for (int i = 0; result.next() && i < rowCount; i++) {
				int addonID = result.getInt("addonID");
				String name = result.getString("name");
				String classDate = result.getString("classDate");
				String timeSlot = result.getString("timeSlot");
				String instructorName = result.getString("instructorName");
				int classLength = result.getInt("classLength");
				double price = result.getDouble("price");

				classes[i] = new Class(name, price, classDate, timeSlot, instructorName, classLength, addonID);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

		return classes;
	}

	public void saveEnrollment(int memberID, String membershipName, int addonID) {
		String sql = "insert into Enrollment(OwnedMembership_memberID, OwnedMembership_Membership_name, Addon_addonID) " +
				"values (?,?,?);";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,memberID);
			statement.setString(2,membershipName);
			statement.setInt(3,addonID);
			statement.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	//given the customer accountID and the membershipName, create/insert a new ownedMemberhsip and return the ownedMembershipID
	public int createOwnedMembership(int custID, String membershipName) {
		int memberID = 0;
		String purchaseDate = LocalDate.now().toString();
		String renewalDate;

		int year = Integer.parseInt(purchaseDate.substring(0,4));
		int month = Integer.parseInt(purchaseDate.substring(5,7));
		int day = Integer.parseInt(purchaseDate.substring(8));

		if(membershipName.equals("weekly")) {
			renewalDate = java.time.LocalDate.of(year,month,day).plusWeeks(1).toString();
		}else if(membershipName.equals("monthly")) {
			renewalDate = java.time.LocalDate.of(year,month,day).plusMonths(6).toString();
		}else{//yearly
			renewalDate = java.time.LocalDate.of(year,month,day).plusMonths(12).toString();
		}


		String sql = "insert into OwnedMembership(startDate, renewalDate, status, Membership_name) values (?,?,?,?);";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,purchaseDate);
			statement.setString(2,renewalDate);
			statement.setString(3,"active");
			statement.setString(4,membershipName);
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();
			memberID = 0;
			if(rs.next()) {
				memberID = rs.getInt(1);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return memberID;
	}

	public Membership getMembership(Customer customer) {
		int memberID = getMemberID(customer);
		Membership membership = new Membership();
		String sql = "select * from OwnedMembership where memberID=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,memberID);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				membership.setMembershipID(rs.getInt("memberID"));
				membership.setName(rs.getString("Membership_name"));
				membership.setMembershipStartDate(rs.getString("startDate"));
				membership.setMembershipRenewalDate(rs.getString("renewalDate"));

				String isActive = rs.getString("status");
				if(isActive.equals("active")) {
					membership.setMembershipActive(true);
				}else {
					membership.setMembershipActive(false);
				}

				//get the price from the membership table
				sql = "select * from Membership where name=?;";
				PreparedStatement st = connection.prepareStatement(sql);
				st.setString(1, rs.getString("Membership_name"));
				ResultSet result = st.executeQuery();
				if(result.next()) {
					membership.setPrice(result.getDouble("price"));
				}else {
					System.out.println("Could not find referencing membership");
					membership = null;
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			membership = null;
		}
		return membership;
	}

	//This method assumes the entry found from the query is the "correct" OwnedMembership (not an old, outdated one)
	public int getMemberID(Customer customer) {
		int id = customer.getId();
		int memberID = -1;
		String sql = "SELECT * FROM MembershipSale WHERE Customer_Account_accountID=? ORDER BY purchaseID DESC;";
		try{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				memberID = rs.getInt("OwnedMembership_memberID");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return memberID;
	}

	public void saveSale(int total, int id, int memberID, String membershipName) {
		String sql = "insert into MembershipSale" +
				"(purchaseTime, totalAmount, Customer_Account_accountID, OwnedMembership_memberID, OwnedMembership_Membership_name) " +
				"values (?,?,?,?,?);";
		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,getStartDate(memberID));
			statement.setInt(2,total);
			statement.setInt(3,id);
			statement.setInt(4,memberID);
			statement.setString(5,getMembershipName(memberID));
			statement.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	//get start date associated with the given memberID (returns empty string if no memberID is found)
	private String getStartDate(int memberID) {
		String startDate = "";
		String sql = "select * from OwnedMembership where memberID=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,memberID);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				startDate = rs.getString("startDate");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return startDate;
	}

	//get membership name associated with the given memberID (returns empty string if no memberID is found)
	public String getMembershipName(int memberID) {
		String name = "";
		String sql = "select * from OwnedMembership where memberID=?;";
		try{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,memberID);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				name = rs.getString("Membership_name");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return name;
	}


	//get Addons list given the owned-membershipID
	public Addon[] getAddons(Membership Omembership) {
		Addon[] addons = null;

		String row =
				"select count(*) As numOfRows from (" +
						"SELECT * " +
						"FROM Enrollment where OwnedMembership_memberID=? " +
						"and OwnedMembership_Membership_name=?" +
						") t;";

		String sql = "select * from Enrollment where OwnedMembership_memberID=?" +
				" and OwnedMembership_Membership_name=?;";
		try {
			PreparedStatement st = connection.prepareStatement(row);
			st.setInt(1,Omembership.getMembershipID());
			st.setString(2,Omembership.getName());
			ResultSet r = st.executeQuery();
			int rowCount = 0;
			if(r.next()) {
				rowCount = r.getInt("numOfRows");
			}

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,Omembership.getMembershipID());
			statement.setString(2,Omembership.getName());
			ResultSet rs = statement.executeQuery();

			addons = new Addon[rowCount];
			//for every addonID found, get the addon object
			for(int i = 0; i < addons.length; i++) {
				rs.next();
				int addonID = rs.getInt("Addon_addonID");
				addons[i] = getAddon(addonID);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return addons;
	}

	//get Addons list given addonID
	public Addon getAddon(int addonID) {
		Addon addon = null;
		String name = "";
		double price = 0;

		String sql = "select * from Addon where addonID=?;";
		try{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,addonID);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				name = rs.getString("name");
				price = rs.getDouble("price");
			}

			//figure out if addon is a class or personal trainer (and get the details to return it)
			sql = "select * from Class where Addon_addonID=?;";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1,addonID);
			rs = st.executeQuery();
			if(rs.next()) {
				String classDate = rs.getString("classDate");
				String timeSlot = rs.getString("timeSlot");
				String instructorName = rs.getString("instructorName");
				int  classLength = rs.getInt("classLength");
				addon = new Class(name, price, classDate, timeSlot, instructorName, classLength, addonID);

			}else {//not a class, but a personal trainer
				sql = "select * from PersonalTrainer where addonID=?;";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setInt(1,addonID);
				rs = stmt.executeQuery();
				if(rs.next()) {
					int trainerID = rs.getInt("trainerID");
					String bookingDate = rs.getString("bookingDate");
					String trainerName = rs.getString("trainerName");
					addon = new PersonalTrainer(addonID, name, price, trainerName, bookingDate, trainerID);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return addon;
	}

	//given the customer, cancel the subscription (set status to inactive in OwnedMembership)
	public void cancelSubscription(Customer customer) {
		int memberID = getMemberID(customer);
		String sql = "update OwnedMembership set status=? where memberID=?;";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,"Inactive");
			statement.setInt(2,memberID);
			statement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Could not cancel Subscription");
		}

	}

	//returns true if the membership status is active, false otherwise
	public boolean getMembershipStatus(int memberID) {
		boolean status = false;
		String sql = "select * from OwnedMembership where memberID=?;";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,memberID);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				if( rs.getString("status").equals("active") ) {
					status = true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return status;
	}
}
