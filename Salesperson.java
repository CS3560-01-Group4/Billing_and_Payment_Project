/**
 * 
 */

/**
 * @author Cristofer Vargas , , , 
 *
 */
public class Salesperson extends Account {
    int salesPersonID;

    public Salesperson(int salesPersonID, String name, String phoneNumber, String emailAddress) {
        this.salesPersonID = salesPersonID;
        Name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }


    public int getSalesPersonID() {
        return salesPersonID;
    }

    public void setSalesPersonID(int salesPersonID) {
        this.salesPersonID = salesPersonID;
    }


}
