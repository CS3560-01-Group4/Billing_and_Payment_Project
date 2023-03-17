/**
 * 
 */

/**
 * @author Cristofer Vargas , , , 
 *
 */
public class Membership {
    int MembershipID, monthlyBalance;
    String MembershipStartDate,MembershipRenewalDate;
    boolean membershipActive;

    Addon[] listOfAddons;


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

    public int getMonthlyBalance() {
        return monthlyBalance;
    }

    public void setMonthlyBalance(int monthlyBalance) {
        this.monthlyBalance = monthlyBalance;
    }


}
