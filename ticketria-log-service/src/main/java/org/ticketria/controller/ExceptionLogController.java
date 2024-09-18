package org.ticketria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ticketria.model.ExceptionLogs;
import org.ticketria.service.ExceptionLogService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
@RequiredArgsConstructor
public class ExceptionLogController {

    private final ExceptionLogService exceptionLogService;


    @GetMapping
    public ResponseEntity<List<ExceptionLogs>> getAllLogs() {
        List<ExceptionLogs> logs = exceptionLogService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExceptionLogs> getLogById(@PathVariable String id) {
        ExceptionLogs log = exceptionLogService.getLogById(id);
        return ResponseEntity.ok(log);
    }
}
