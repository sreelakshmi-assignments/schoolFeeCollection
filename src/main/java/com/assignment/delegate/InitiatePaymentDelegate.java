package com.assignment.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("initiatePaymentDelegate")
public class InitiatePaymentDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        Double amount = (Double) execution.getVariable("amount");

        if (amount != null) {
            double value = amount;
            // Proceed with logic
        } else {
            // Handle missing variable gracefully
            throw new RuntimeException("Payment amount is missing in process variables.");
        }
        // Simulate payment
        execution.setVariable("paymentId", UUID.randomUUID().toString());
        execution.setVariable("paymentStatus", "SUCCESS");
    }
}

