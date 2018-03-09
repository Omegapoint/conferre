package se.omegapoint.conferre.registration.domain;

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
public class RegistrationRepository implements EventListener {

    private EventBus eventBus;

    private Map<Identity,Registration> currentState = new HashMap<>();

    @Autowired
    public RegistrationRepository(EventBus eventBus) {
        this.eventBus = eventBus;
        createCurrentState();
        eventBus.registerListener(this);
    }

    public List<Registration> listRegistrations() {
        return new ArrayList<>(currentState.values());
    }

    private void createCurrentState() {
        eventBus.list("registration").forEach(this::onNext);
    }

    public void onNext(Event event) {
        if (validate(event)) {
            eventBus.publish("registration_external", event);
            Registration registration = (Registration) event.getData();
            currentState.put(registration.getId(), registration);
        }
    }

    @Override
    public String getTopicName() {
        return "registration";
    }

    private boolean validate(Event e) {
        Registration registration = ((Registration) e.getData());
        return currentState.values().stream().noneMatch(registration::conflictsWith);
    }
}
