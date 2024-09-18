package org.ticketria.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.ticketria.exception.ApiError;
import org.ticketria.producer.constants.KafkaTopicConstants;


@Component
@RequiredArgsConstructor
@Slf4j
public final class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendExceptionLog(ApiError apiError)
    {
        kafkaTemplate.send(KafkaTopicConstants.EXCEPTION_TICKET_LOG_INDEX_TOPIC,apiError);
    }

}