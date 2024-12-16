package dev.evento.examples.greeting;

import java.util.ArrayList;
import java.util.List;

public class Greeter {
    private String name;

    public Greeter(String name) {
        this.name = name;
    }

    public ArrivalEvent arrive (Arrive command, Greeters others) {
        List<Greeter> otherGreeters = new ArrayList<>();

        int start = 0;
//        do {
//            otherGreeters.addAll(others.list(start, command.getMinGreetings()))
//        }
        return new SaidHello("Hello world!");
    }

    public DepartureEvent depart (Depart command) {
        return null;
    }

    public ResponseEvent respond (Respond command) {
        switch (command.getMessage()) {
            case HELLO: return new SaidHello(command.getTo(), "Hello " + command.getTo() +"!");
            case GOODBYE: return new SaidGoodbye();
            default:
                throw new IllegalStateException("Unexpected value: " + command.getMessage());
        }
    }
}
