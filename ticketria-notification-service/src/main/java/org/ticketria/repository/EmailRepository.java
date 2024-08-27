package org.ticketria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.ticketria.model.Email;

import java.util.List;

public interface EmailRepository extends MongoRepository<Email, String> {

    List<Email> findByTo(String email);
}

