package se.omegapoint.conferre.proposal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;
import se.omegapoint.conferre.event.TopicName;
import se.omegapoint.conferre.proposal.domain.Proposal;
import se.omegapoint.conferre.proposal.domain.ProposalRules;
import se.omegapoint.conferre.proposal.domain.ProposalStore;
import se.omegapoint.conferre.proposal.event.ProposalTitleUpdated;

import java.util.List;

import static se.omegapoint.conferre.event.TopicName.PROPOSAL;

@Service
public class ProposalService {

    private final ProposalStore store;
    private ProposalRules rules;
    private EventBus eventBus;

    @Autowired
    public ProposalService(ProposalStore store, ProposalRules rules, EventBus eventBus) {
        this.store = store;
        this.rules = rules;
        this.eventBus = eventBus;
    }

    public synchronized Proposal createProposal(Proposal proposal) {
        rules.validateCreated(store, proposal);
        Event event = new Event("CREATED", proposal);
        return publish(event);
    }

    public synchronized Proposal updateTitle(Identity proposalId, String title) {
        rules.validateExists(store, proposalId);
        rules.validateUpdateTitle(store, proposalId, title);
        Event event = new Event("TITLE_UPDATED", new ProposalTitleUpdated(proposalId, title));
        return publish(event);
    }

    private Proposal publish(Event event) {
        eventBus.publish(PROPOSAL, event);
        return store.applyEvent(event);
    }

    public List<Proposal> listProposals() {
        return store.listProposals();
    }
}
