package se.omegapoint.conferre.proposal.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.conference.domain.Conference;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;
import se.omegapoint.conferre.event.EventListener;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ConferenceProposalRepository implements EventListener {
    private EventBus eventBus;

    private Set<Identity> currentState = new HashSet<>();

    @Autowired
    public ConferenceProposalRepository(EventBus eventBus) {
        this.eventBus = eventBus;
        createCurrentState();
        eventBus.registerListener(this);
    }

    private void createCurrentState() {
        eventBus.list("conference").forEach(this::applyEvent);
    }

    public void onNext(Event event) {
        System.out.println(event);
        applyEvent(event);
    }

    private void applyEvent(Event event) {
        Conference conference = (Conference) event.getData();
        currentState.add(conference.getId());
    }

    public boolean exists(Identity conferenceId) {
        return currentState.contains(conferenceId);
    }

    @Override
    public String getTopicName() {
        return "conference";
    }

}
