public class PurchasedMembership {
    String purchaseDateAndTime;
    double tax, finalAmt;
    int purchaseID;

    public String getPurchaseDateAndTime() {
        return purchaseDateAndTime;
    }

    public void setPurchaseDateAndTime(String purchaseDateAndTime) {
        this.purchaseDateAndTime = purchaseDateAndTime;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getFinalAmt() {
        return finalAmt;
    }

    public void setFinalAmt(double finalAmt) {
        this.finalAmt = finalAmt;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }
}
