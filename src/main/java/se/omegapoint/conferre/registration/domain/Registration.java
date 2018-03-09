package se.omegapoint.conferre.registration.domain;

import se.omegapoint.conferre.Entity;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventType;

import java.time.LocalDateTime;

import static se.omegapoint.conferre.event.EventType.CREATED;

public class Registration extends Entity {

    private final String eMail;
    private final Identity conferenceId;

    public Registration(String eMail, Identity conferenceId) {
        super(Identity.instance());

        this.eMail = eMail;
        this.conferenceId = conferenceId;
    }

    public Identity getConferenceId() {
        return conferenceId;
    }

    public String getEMail() {
        return eMail;
    }

    public Event asCreatedEvent() {
        return new Event(CREATED, this);
    }
}
