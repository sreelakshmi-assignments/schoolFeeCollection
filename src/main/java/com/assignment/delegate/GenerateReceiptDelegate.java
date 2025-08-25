package com.assignment.delegate;
import com.assignment.dto.ReceiptDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("generateReceiptDelegate")
public class GenerateReceiptDelegate implements JavaDelegate {

    private final RestTemplate restTemplate;

    public GenerateReceiptDelegate(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public void execute(DelegateExecution execution) {
        ReceiptDTO receipt = new ReceiptDTO();
        receipt.setStudentId((String) execution.getVariable("studentId"));
        receipt.setPaymentId((String) execution.getVariable("paymentId"));
        receipt.setAmount((double) execution.getVariable("amount"));

        restTemplate.postForEntity("http://localhost:8080/api/receipts", receipt, Void.class);
    }
}


