package se.omegapoint.conferre.registration.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;
import se.omegapoint.conferre.event.TopicName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static se.omegapoint.conferre.event.TopicName.REGISTRATION;

@Repository
public class RegistrationRepository {

    private EventBus eventBus;
    private ConferenceRegistrationRepository conferenceRepository;

    private Map<Identity, Registration> currentState = new HashMap<>();

    @Autowired
    public RegistrationRepository(EventBus eventBus, ConferenceRegistrationRepository conferenceRepository) {
        this.eventBus = eventBus;
        this.conferenceRepository = conferenceRepository;
        createCurrentState();
    }

    public List<Registration> listRegistrations() {
        return new ArrayList<>(currentState.values());
    }

    private void createCurrentState() {
        eventBus.list(REGISTRATION).forEach(this::applyEvent);
    }

    private void applyEvent(Event event) {
        Registration registration = (Registration) event.getData();
        currentState.put(registration.getId(), registration);
    }

    private void validate(Registration registration) {
        currentState.values().forEach(registration::requireGood);
        validateConference(registration);
    }

    private void validateConference(Registration registration) {
        if (!conferenceRepository.exists(registration.getConferenceId())) {
            throw new IllegalStateException("Conference does not exist: " + registration.getConferenceId());
        }
    }

    public void createRegistration(Registration registration) {
        validate(registration);
        Event event = registration.asCreatedEvent();
        eventBus.publish(REGISTRATION , event);
        applyEvent(event);
    }
}
