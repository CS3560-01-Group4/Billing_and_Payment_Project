public class Address {
    private int addressID;
    private String streetName, city, zipCode, state;

    public Address(String inputAddress, String inputCity, String inputZip, String inputState) {
        streetName = inputAddress;
        city = inputCity;
        zipCode = inputZip;
        state = inputState;
    }

    public int getAddressID() {
        return addressID;
    }
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }


    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }


    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
