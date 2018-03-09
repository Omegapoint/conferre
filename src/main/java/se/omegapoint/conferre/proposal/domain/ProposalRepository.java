package se.omegapoint.conferre.proposal.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;
import se.omegapoint.conferre.registration.domain.ConferenceRegistrationRepository;
import se.omegapoint.conferre.registration.domain.Registration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProposalRepository {

    private EventBus eventBus;
    private ConferenceRegistrationRepository conferenceRepository;

    private Map<Identity, Proposal> currentState = new HashMap<>();

    @Autowired
    public ProposalRepository(EventBus eventBus, ConferenceRegistrationRepository conferenceRepository) {
        this.eventBus = eventBus;
        this.conferenceRepository = conferenceRepository;
        createCurrentState();
    }

    public List<Proposal> listProposals() {
        return new ArrayList<>(currentState.values());
    }

    private void createCurrentState() {
        eventBus.list("proposal").forEach(this::applyEvent);
    }

    private void applyEvent(Event event) {
        Proposal proposal = (Proposal) event.getData();
        currentState.put(proposal.getId(), proposal);
    }

    private void validate(Proposal proposal) {
        currentState.values().forEach(proposal::requireGood);
        validateConference(proposal);
    }

    private void validateConference(Proposal proposal) {
        if (!conferenceRepository.exists(proposal.getConferenceId())) {
            throw new IllegalStateException("Conference does not exist: " + proposal.getConferenceId());
        }
    }

    public void createProposal(Proposal registration) {
        validate(registration);
        Event event = registration.asCreatedEvent();
        eventBus.publish("proposal", event);
        applyEvent(event);
    }
}
