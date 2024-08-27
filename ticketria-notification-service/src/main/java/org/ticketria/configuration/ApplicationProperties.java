package org.ticketria.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "app")
@Configuration
public class ApplicationProperties {

    private Email email;
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public record Email(
            String username,
            String password,
            String host,
            int port,
            String from
    ){}

    public record Client(
            String host
    ){}

}
