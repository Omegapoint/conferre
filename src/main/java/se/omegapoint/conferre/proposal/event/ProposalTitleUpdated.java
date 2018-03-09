package se.omegapoint.conferre.proposal.event;

import se.omegapoint.conferre.Identity;

public class ProposalTitleUpdated {

    private final Identity proposalId;
    private final String title;

    public ProposalTitleUpdated(Identity proposalId, String title) {
        this.proposalId = proposalId;
        this.title = title;
    }

    public Identity getProposalId() {
        return proposalId;
    }

    public String getTitle() {
        return title;
    }
}
