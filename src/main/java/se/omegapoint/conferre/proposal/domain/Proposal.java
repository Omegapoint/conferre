package se.omegapoint.conferre.proposal.domain;

import se.omegapoint.conferre.Entity;
import se.omegapoint.conferre.Identity;

public class Proposal extends Entity {

    private String title;
    private String description;
    private String email;
    private Identity conferenceId;

    //For Jacksson
    public Proposal() {
        super(Identity.instance());
    }

    public Proposal(String title, String description, String email, Identity conferenceId) {
        this(Identity.instance(), title, description, email, conferenceId);
    }

    private Proposal(Identity identity, String title, String description, String email, Identity conferenceId) {
        super(identity);

        this.title = title;
        this.description = description;
        this.email = email;
        this.conferenceId = conferenceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public Identity getConferenceId() {
        return conferenceId;
    }

    public void requireGood(Proposal existing) {
        if (title.equals(existing.title) && email.equals(existing.email) && conferenceId.equals(existing.conferenceId)) {
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
            this.eMail = proposal.email;
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
