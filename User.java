/**
 * 
 */

/**
 * @author Cristofer Vargas , , , 
 *
 */
public class User {
    String Name;
    int UserID;
    int PhoneNumber;
    int CreditCardNumber;
    String emailAddress;
    String Address;

    public int getUserID() {
        return UserID;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public int getCreditCardNumber() {
        return CreditCardNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return Address;
    }

    String getName(String Name){
        return Name;
    }


    public void setUserID(int userID) {
        UserID = userID;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        CreditCardNumber = creditCardNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAddress(String address) {
        Address = address;
    }












}
