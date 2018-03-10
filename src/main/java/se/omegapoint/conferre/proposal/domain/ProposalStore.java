package se.omegapoint.conferre.proposal.domain;

import org.springframework.stereotype.Component;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.proposal.event.ProposalTitleUpdated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
public class ProposalStore {

    private final Map<Identity, Proposal> store = new HashMap<>();

    public void forEach(BiConsumer<Identity, Proposal> mapFunction) {
        store.forEach(mapFunction);
    }

    public Proposal applyEvent(Event event) {
        switch (event.getType()) {
            case "CREATED":
                return created(event);
            case "TITLE_UPDATED":
                return titleUpdated(event);
            default:
                throw new UnsupportedOperationException("Unhandled event type: " + event.getType());
        }
    }

    private Proposal created(Event event) {
        Proposal proposal = (Proposal) event.getPayload();
        store.put(proposal.getId(), proposal);
        return proposal;
    }

    private Proposal titleUpdated(Event event) {
        ProposalTitleUpdated titleUpdated = (ProposalTitleUpdated) event.getPayload();
        Proposal existing = store.get(titleUpdated.getProposalId());
        Proposal updated = Proposal.from(existing).withTitle(titleUpdated.getTitle()).build();
        store.put(updated.getId(), updated);
        return updated;
    }

    public boolean contains(Identity proposalId) {
        return store.containsKey(proposalId);
    }

    public Proposal get(Identity proposalId) {
        return store.get(proposalId);
    }

    public List<Proposal> listProposals() {
        return new ArrayList<>(store.values());
    }
}
