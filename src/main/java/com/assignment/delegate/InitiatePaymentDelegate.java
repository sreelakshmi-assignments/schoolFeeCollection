package com.assignment.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("initiatePaymentDelegate")
public class InitiatePaymentDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        double amount = (double) execution.getVariable("amount");
        // Simulate payment
        execution.setVariable("paymentId", UUID.randomUUID().toString());
        execution.setVariable("paymentStatus", "SUCCESS");
    }
}

