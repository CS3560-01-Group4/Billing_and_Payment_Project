/**
 * 
 */

/**
 * @author Cristofer Vargas , , , 
 *
 */
public class Salesperson {
    int salesPersonID;
    String Name, phoneNumber, emailAddress;

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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
