package org.ticketria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ticketria.model.Email;
import org.ticketria.repository.EmailRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailRepository emailRepository;

    @GetMapping("/{emailAddress}")
    public ResponseEntity<List<Email>> getEmailsByEmail(@PathVariable String emailAddress) {
        List<Email> emails = emailRepository.findByTo(emailAddress);
        if (emails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emails);
    }
}