package se.omegapoint.conferre.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;
import se.omegapoint.conferre.registration.domain.Registration;
import se.omegapoint.conferre.registration.domain.RegistrationRules;
import se.omegapoint.conferre.registration.domain.RegistrationStore;

import java.util.List;

import static se.omegapoint.conferre.event.TopicName.REGISTRATION;

@Service
public class RegistrationService {

    private RegistrationStore store;
    private RegistrationRules rules;
    private EventBus eventBus;

    @Autowired
    public RegistrationService(RegistrationStore store, RegistrationRules rules, EventBus eventBus) {
        this.store = store;
        this.rules = rules;
        this.eventBus = eventBus;
    }

    public Registration createRegistration(Registration registration) {
        rules.validateCreated(store, registration);
        rules.validateConference(registration);
        Event event = new Event("CREATED", registration);
        return publish(event);
    }

    public List<Registration> listRegistrations() {
        return store.listRegistrations();
    }

    private Registration publish(Event event) {
        eventBus.publish(REGISTRATION, event);
        return store.applyEvent(event);
    }
}
