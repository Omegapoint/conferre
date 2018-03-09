package se.omegapoint.conferre.event;

import java.util.ArrayList;
import java.util.List;

class EventTopic {

    private final List<Event> events = new ArrayList<>();

    void publish(Event event) {
        events.add(event);
    }

    public List<Event> list() {
        return events;
    }
}
