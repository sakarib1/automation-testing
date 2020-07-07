package edu.balu.test.automate.simple.junit;

import edu.balu.test.automate.model.EmployeeDetails;
import edu.balu.test.automate.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EmployeeService.class})
@DisplayName("Employee Tests")
public class SimpleJUnitEmployeeTestCases {

    Logger log = LoggerFactory.getLogger(SimpleJUnitEmployeeTestCases.class);


    @Autowired
    EmployeeService employeeService;

    EmployeeDetails employee = new EmployeeDetails();


    //test to check appraisal
    @Test
    @DisplayName("Calculate Employee Appraisal")
    public void testCalculateAppriasal() {
        employee.setName("Rajeev");
        employee.setAge(25);
        employee.setMonthlySalary(8000);

        double appraisal = employeeService.calculateAppraisal(employee);
        log.info("Appraisal=="+appraisal);
        assertEquals(500, appraisal, 0.0);
    }

    // test to check yearly salary
    @Test
    @DisplayName("Calculate Yearly Salary")
    public void testCalculateYearlySalary() {
        employee.setName("Rajeev");
        employee.setAge(25);
        employee.setMonthlySalary(8000);

        double salary = employeeService.calculateYearlySalary(employee);
        assertEquals(96000, salary, 0.0);
    }



}
