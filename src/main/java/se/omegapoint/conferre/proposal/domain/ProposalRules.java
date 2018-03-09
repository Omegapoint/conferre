package se.omegapoint.conferre.proposal.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.omegapoint.conferre.Identity;

@Component
public class ProposalRules {

    private ConferenceProposalRepository conferenceRepository;

    @Autowired
    public ProposalRules(ConferenceProposalRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public void validateCreated(ProposalStore store, Proposal proposal) {
        store.forEach((id, existing) -> proposal.requireGood(existing));
        validateConference(proposal);
    }

    private void validateConference(Proposal proposal) {
        if (!conferenceRepository.exists(proposal.getConferenceId())) {
            throw new IllegalStateException("Conference does not exist: " + proposal.getConferenceId());
        }
    }

    public void validateExists(ProposalStore store, Identity proposalId) {
        if (!store.contains(proposalId)) {
            throw new IllegalArgumentException("No such proposal: " + proposalId);
        }
    }

    public void validateUpdateTitle(ProposalStore store, Identity proposalId, String title) {
        Proposal updated = Proposal.from(store.get(proposalId)).withTitle(title).build();
        store.forEach((id, existing) -> updated.requireGood(existing));
    }
}
