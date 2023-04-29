public class Class extends Addon{
    public String classDate, timeSlot, instructorName;
    public int classLength;

    public Class(String name, double price, String classDate, String timeSlot, String instructorName, int classLength, int addonID) {
        super.setAddonID(addonID);
        super.setName(name);
        super.setPrice(price);
        this.classDate = classDate;
        this.timeSlot = timeSlot;
        this.instructorName = instructorName;
        this.classLength = classLength;
    }
}
