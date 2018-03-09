package se.omegapoint.conferre.proposal.domain;

import se.omegapoint.conferre.Entity;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;

public class Proposal extends Entity {

    private final String title;
    private final String description;
    private final String eMail;
    private final Identity conferenceId;

    public Proposal(String title, String description, String eMail, Identity conferenceId) {
        this(Identity.instance(), title, description, eMail, conferenceId);
    }

    private Proposal(Identity identity, String title, String description, String eMail, Identity conferenceId) {
        super(identity);

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

    public static ProposalBuilder from(Proposal proposal) {
        return new ProposalBuilder(proposal);
    }

    static class ProposalBuilder {

        private final Identity identity;
        private String title;
        private String description;
        private String eMail;
        private Identity conferenceId;

        public ProposalBuilder(Proposal proposal) {
            this.identity = proposal.getId();
            this.title = proposal.title;
            this.description = proposal.description;
            this.eMail = proposal.eMail;
            this.conferenceId = proposal.conferenceId;
        }

        public ProposalBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Proposal build() {
            return new Proposal(identity, title, description, eMail, conferenceId);
        }
    }
}
