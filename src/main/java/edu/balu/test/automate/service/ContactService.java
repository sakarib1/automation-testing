package edu.balu.test.automate.service;

import edu.balu.test.automate.model.employee.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    // Calculate the yearly subscription amount
    public double calculateYearlySubscriptionAmount(Contact contact) {
        double yearlySalary = 0;
        yearlySalary = contact.getMonthlySubscriptionAmount() * 12;
        return yearlySalary;
    }

    // Calculate the discount amount of employee
    public double calculateDiscount(Contact contact) {
        double discount = 0;

        if(contact.getMonthlySubscriptionAmount() > 10000){
            discount = 500;
        }else{
            discount = 1000;
        }

        return discount;
    }
}