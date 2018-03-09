package se.omegapoint.conferre.registration.domain;

import org.springframework.lang.NonNull;
import se.omegapoint.conferre.Entity;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;

import static se.omegapoint.conferre.event.EventType.CREATED;

public class Registration extends Entity {

    private final String eMail;
    private final Identity conferenceId;

    public Registration(@NonNull String eMail, @NonNull Identity conferenceId) {
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

    public void requireGood(Registration existing) {
        if (eMail.equals(existing.eMail) && conferenceId.equals(existing.conferenceId)) {
            throw new IllegalStateException("Duplicate registration, eMail: " + existing.eMail + ", conferenceId: " + existing.conferenceId);
        }
    }
}
