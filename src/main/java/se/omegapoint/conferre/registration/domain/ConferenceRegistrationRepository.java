package se.omegapoint.conferre.registration.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.conference.domain.Conference;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;
import se.omegapoint.conferre.event.EventListener;
import se.omegapoint.conferre.event.TopicName;

import java.util.HashSet;
import java.util.Set;

import static se.omegapoint.conferre.event.TopicName.CONFERENCE;

@Repository
public class ConferenceRegistrationRepository implements EventListener {
    private EventBus eventBus;

    private Set<Identity> currentState = new HashSet<>();

    @Autowired
    public ConferenceRegistrationRepository(EventBus eventBus) {
        this.eventBus = eventBus;
        createCurrentState();
        eventBus.registerListener(this);
    }

    private void createCurrentState() {
        eventBus.list(CONFERENCE).forEach(this::applyEvent);
    }

    public void onNext(Event event) {
        applyEvent(event);
    }

    private void applyEvent(Event event) {
        Conference conference = (Conference) event.getPayload();
        currentState.add(conference.getId());
    }

    public boolean exists(Identity conferenceId) {
        return currentState.contains(conferenceId);
    }

    @Override
    public TopicName getTopicName() {
        return CONFERENCE;
    }

}
