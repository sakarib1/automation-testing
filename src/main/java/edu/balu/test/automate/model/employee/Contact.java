package edu.balu.test.automate.model.employee;

public class Contact {

    private String name;
    private String taxId;
    private double monthlySubscriptionAmount;
    private PostalAddress postalAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthlySubscriptionAmount() {
        return monthlySubscriptionAmount;
    }

    public void setMonthlySubscriptionAmount(double monthlySubscriptionAmount) {
        this.monthlySubscriptionAmount = monthlySubscriptionAmount;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }
}
