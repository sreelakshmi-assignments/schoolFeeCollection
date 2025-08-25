package com.assignment.controller;
import com.assignment.model.FeeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fees")
@Tag(name = "Student API", description = "Operations related to student data")
public class FeeCollectionController {
    @Autowired
    private RuntimeService runtimeService;

    @PostMapping("/collect")
    @Operation(
            summary = "Start Fee Collection Process",
            description = "Initiates the fee collection workflow for a student using their ID and fee amount."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Process started successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<String> startFeeCollection(@RequestBody FeeRequest request) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("studentId", request.getStudentId());
        vars.put("amount", request.getAmount());
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("feeCollectionProcess", vars);
        return ResponseEntity.ok(instance.getProcessInstanceId());
    }
}
