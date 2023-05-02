/**
 * 
 */

/**
 * @author Cristofer Vargas , , , 
 *
 */
public class Membership {
    int MembershipID = -1;
    double price;
    String MembershipStartDate;
    String MembershipRenewalDate;
    String name;
    boolean membershipActive;

    Addon[] listOfAddons;

    public Membership() {
        ;
    }

    public int getMembershipID() {
        return MembershipID;
    }

    public void setMembershipID(int membershipID) {
        MembershipID = membershipID;
    }

    public String getMembershipStartDate() {
        return MembershipStartDate;
    }

    public void setMembershipStartDate(String membershipStartDate) {
        MembershipStartDate = membershipStartDate;
    }

    public String getMembershipRenewalDate() {
        return MembershipRenewalDate;
    }

    public void setMembershipRenewalDate(String membershipRenewalDate) {
        MembershipRenewalDate = membershipRenewalDate;
    }

    public boolean isMembershipActive() {
        return membershipActive;
    }

    public void setMembershipActive(boolean membershipActive) {
        this.membershipActive = membershipActive;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }


}
