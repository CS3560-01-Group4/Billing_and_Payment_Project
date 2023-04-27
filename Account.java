abstract class Account {

    int id;
    String Name, phoneNumber, emailAddress, password;


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

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

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }
}
