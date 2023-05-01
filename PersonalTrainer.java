public class PersonalTrainer extends Addon{
    String trainerName, bookingDate;
    int trainerID;

    public PersonalTrainer(int addonID, String name, double price, String trainerName, String bookingDate, int trainerID) {
        super.setAddonID(addonID);
        super.setName(name);
        super.setPrice(price);
        this.trainerName = trainerName;
        this.bookingDate = bookingDate;
        this.trainerID = trainerID;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setTrainerID(int trainerID) { this.trainerID = trainerID; }

    public int getTrainerID() { return trainerID; }
}
