package com.assignment.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("fetchStudentDelegate")
public class FetchStudentDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String studentId = (String) execution.getVariable("studentId");
        // Simulate call to student-service
        execution.setVariable("studentName", "John Doe");
    }
}
