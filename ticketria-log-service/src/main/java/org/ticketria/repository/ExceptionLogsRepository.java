package org.ticketria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.ticketria.model.ExceptionLogs;

@Repository
public interface ExceptionLogsRepository extends MongoRepository<ExceptionLogs,String> {
}