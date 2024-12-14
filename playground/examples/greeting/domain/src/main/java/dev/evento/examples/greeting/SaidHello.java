package dev.evento.examples.greeting;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SaidHello implements ResponseEvent, ArrivalEvent {
    private Set<String> recipients;
    private String message;

    public SaidHello(String message, String...recipient) {
        this.recipients = Arrays.asList(recipient).stream().collect(Collectors.toSet());
        this.message = message;
    }

    public Set<String> getRecipients() {
        return this.recipients.stream().collect(Collectors.toSet());
    }
}
