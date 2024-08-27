package org.ticketria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "emails")
public class Email {

    @Id
    private String id;
    private String to;
    private String subject;
    private String body;
    private LocalDateTime sentAt;

    public Email(String to, String subject, String body, LocalDateTime sentAt) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.sentAt = sentAt;
    }

}
