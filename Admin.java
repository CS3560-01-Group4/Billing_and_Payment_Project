/**
 * 
 */

/**
 * @author Cristofer Vargas , , , 
 *
 */
public class Admin extends Account {
    int sysAdminID;
    String sysAdminUsername,password;


    public int getSysAdminID() {
        return sysAdminID;
    }

    public void setSysAdminID(int sysAdminID) {
        this.sysAdminID = sysAdminID;
    }

    public String getSysAdminUsername() {
        return sysAdminUsername;
    }

    public void setSysAdminUsername(String sysAdminUsername) {
        this.sysAdminUsername = sysAdminUsername;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Adds a new sales person to the database using information collected from the newly hired employee. The ID is randomly generated
     * using some function to generate random numbers.
     * @param name
     * @param emailAddress
     * @param ID
     * @param emailAddress
     * @return newSalesPerson
     */
    public Salesperson addSalesPerson(String name, String phoneNumber, int ID, String emailAddress){
        Salesperson newSalesPerson = new Salesperson(ID,name, phoneNumber, emailAddress);
        return newSalesPerson;



    }

}
