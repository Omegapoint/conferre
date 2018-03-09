package se.omegapoint.conferre.proposal.domain;

import se.omegapoint.conferre.Entity;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;

import static se.omegapoint.conferre.event.EventType.CREATED;

public class Proposal extends Entity {

    private final String title;
    private final String description;
    private final String eMail;
    private final Identity conferenceId;

    public Proposal(String title, String description, String eMail, Identity conferenceId) {
        super(Identity.instance());

        this.title = title;
        this.description = description;
        this.eMail = eMail;
        this.conferenceId = conferenceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String geteMail() {
        return eMail;
    }

    public Identity getConferenceId() {
        return conferenceId;
    }

    public void requireGood(Proposal existing) {
        if (title.equals(existing.title) && eMail.equals(existing.eMail) && conferenceId.equals(existing.conferenceId)) {
            throw new IllegalStateException("Duplicate proposal: " + existing);
        }
    }

    public Event asCreatedEvent() {
        return new Event(CREATED, this);
    }
}
