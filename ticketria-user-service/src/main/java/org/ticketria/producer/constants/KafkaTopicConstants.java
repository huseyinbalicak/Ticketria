package org.ticketria.producer.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopicConstants {
    public static final String EXCEPTION_USER_LOG_INDEX_TOPIC = "exception_user_log_index_topic";
}