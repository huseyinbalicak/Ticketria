package org.ticketria.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.ticketria.model.ExceptionLogs;
import org.ticketria.repository.ExceptionLogsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExceptionLogService {

    private final ExceptionLogsRepository exceptionLogsRepository;

    public List<ExceptionLogs> getAllLogs() {
        return exceptionLogsRepository.findAll();
    }

    public ExceptionLogs getLogById(String id) {
        return exceptionLogsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with id: " + id));
    }
}
