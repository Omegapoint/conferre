package se.omegapoint.conferre.conference.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;
import se.omegapoint.conferre.event.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ConferenceRepository implements EventListener {

    private EventBus eventBus;

    private Map<Identity, Conference> currentState = new HashMap<>();

    @Autowired
    public ConferenceRepository(EventBus eventBus) {
        this.eventBus = eventBus;
        createCurrentState();
        eventBus.registerListener(this);
    }

    public List<Conference> listConferences() {
        return new ArrayList<>(currentState.values());
    }

    private void createCurrentState() {
        eventBus.list("conference").forEach(this::onNext);
    }

    public void onNext(Event event) {
        if (validate(event)) {
            eventBus.publish("conference_external", event);
            Conference conference = (Conference) event.getData();
            currentState.put(conference.getId(), conference);
        }
    }

    @Override
    public String getTopicName() {
        return "conference";
    }

    private boolean validate(Event e) {
        Conference conference = ((Conference) e.getData());
        return currentState.values().stream().noneMatch(conference::conflictsWith);
    }
}
