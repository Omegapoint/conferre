package se.omegapoint.conferre.conference.domain;

import se.omegapoint.conferre.Entity;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;

import static se.omegapoint.conferre.event.EventType.CREATED;

public class Conference extends Entity {

    private final String name;

    public Conference(String name) {
        super(Identity.instance());
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Event asCreatedEvent() {
        return new Event(CREATED, this);
    }

    public void requireGood(Conference existing) {
        if (name.equals(existing.name)) {
            throw new IllegalStateException("Conflicting conference: " + name);
        }
    }
}
