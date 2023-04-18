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

    public void save(DatabaseManager db) throws SQLException {
        db.saveCustomer(this);
    }
}
