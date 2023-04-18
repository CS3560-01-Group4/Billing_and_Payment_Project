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
}
