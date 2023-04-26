public class CreditCard {

    private String cardNumb;
    private String csv;
    private String expMonth;
    private String expDay;

    public CreditCard(String cardNumb, String csv, String expMonth, String expDay) {
        this.cardNumb = cardNumb;
        this.csv = csv;
        this.expMonth = expMonth;
        this.expDay = expDay;
    }
    public String getCardNumb() {
        return cardNumb;
    }

    public void setCardNumb(String cardNumb) {
        this.cardNumb = cardNumb;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpDay() {
        return expDay;
    }

    public void setExpDay(String expDay) {
        this.expDay = expDay;
    }
}
