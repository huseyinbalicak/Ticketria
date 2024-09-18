package org.ticketria.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.ticketria.consumer.constants.KafkaTopicConstants;
import org.ticketria.model.ApiError;
import org.ticketria.model.ExceptionLogs;
import org.ticketria.repository.ExceptionLogsRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final ExceptionLogsRepository exceptionLogsRepository;

    @KafkaListener(topics = KafkaTopicConstants.EXCEPTION_USER_LOG_INDEX_TOPIC, groupId = "ticketria")
    public void consumeExceptionLogsUser(ApiError apiError) {
        log.info("Received exception log for user-service: {}", apiError);

        ExceptionLogs exceptionLogs = new ExceptionLogs();
        exceptionLogs.setApiError(apiError);

        exceptionLogsRepository.save(exceptionLogs);
    }

    @KafkaListener(topics = KafkaTopicConstants.EXCEPTION_TICKET_LOG_INDEX_TOPIC, groupId = "ticketria")
    public void consumeExceptionLogsTickets(ApiError apiError) {
        log.info("Received exception log for ticket-service: {}", apiError);

        ExceptionLogs exceptionLogs = new ExceptionLogs();
        exceptionLogs.setApiError(apiError);

        exceptionLogsRepository.save(exceptionLogs);
    }
}