package se.omegapoint.conferre.conference.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ConferenceRepository {

    private EventBus eventBus;

    private Map<Identity, Conference> currentState = new HashMap<>();

    @Autowired
    public ConferenceRepository(EventBus eventBus) {
        this.eventBus = eventBus;
        createCurrentState();
    }

    public List<Conference> listConferences() {
        return new ArrayList<>(currentState.values());
    }

    private void createCurrentState() {
        eventBus.list("conference").forEach(this::applyEvent);
    }

    private void applyEvent(Event event) {
        Conference conference = (Conference) event.getData();
        currentState.put(conference.getId(), conference);
    }

    private void validate(Conference conference) {
        currentState.values().forEach(conference::requireGood);
    }

    public void createConference(Conference conference) {
        validate(conference);
        Event event = conference.asCreatedEvent();
        eventBus.publish("conference", event);
        applyEvent(event);
    }
}
