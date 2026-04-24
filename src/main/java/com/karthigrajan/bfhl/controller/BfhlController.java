package com.karthigrajan.bfhl.controller;

import com.karthigrajan.bfhl.model.BfhlRequest;
import com.karthigrajan.bfhl.model.BfhlResponse;
import com.karthigrajan.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    @PostMapping
    public ResponseEntity<BfhlResponse> process(@RequestBody BfhlRequest request) {
        return ResponseEntity.ok(bfhlService.processData(request));
    }
}
