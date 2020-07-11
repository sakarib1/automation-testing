package edu.balu.test.automate.tc.billto;

import edu.balu.test.automate.model.employee.Contact;
import edu.balu.test.automate.service.ContactService;
import edu.balu.test.automate.tc.txa.VerificationLoggerExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({SpringExtension.class, VerificationLoggerExtension.class})
@ContextConfiguration(classes = {ContactService.class})
@DisplayName("BillTo Subscription Tests")
public class BillToSubscriptionTests {

    Logger log = LoggerFactory.getLogger(BillToSubscriptionTests.class);


    @Autowired
    ContactService contactService;

    Contact contact = new Contact();


    @Test
    @DisplayName("Calculate Subscription Discount")
    public void testCalculateDiscount() {
        contact.setName("Rajeev");
        contact.setMonthlySubscriptionAmount(8000);

        double discount = contactService.calculateDiscount(contact);
        log.info("Discount=="+discount);
        assertEquals(500, discount, 0.0);
    }

    @Test
    @DisplayName("Calculate Annual Subscription Cost")
    public void testCalculateAnnualSubscriptionCost() {
        contact.setName("Rajeev");
        contact.setMonthlySubscriptionAmount(8000);

        double annulSubscriptionCost = contactService.calculateYearlySubscriptionAmount(contact);
        assertEquals(96000, annulSubscriptionCost, 0.0);
    }



}
