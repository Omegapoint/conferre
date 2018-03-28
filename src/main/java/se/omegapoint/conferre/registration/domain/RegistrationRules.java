package se.omegapoint.conferre.registration.domain;

import org.springframework.stereotype.Component;
import se.omegapoint.conferre.registration.domain.supportive.ConferenceRegistrationRepository;

@Component
public class RegistrationRules {

    private final ConferenceRegistrationRepository conferenceRepository;

    public RegistrationRules(ConferenceRegistrationRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public void validateCreated(RegistrationStore store, Registration registration) {
        store.forEach((id, existing) -> registration.requireGood(existing));
    }

    public void validateConference(Registration registration) {
        if (!conferenceRepository.exists(registration.getConferenceId())) {
            throw new IllegalStateException("Conference does not exist: " + registration.getConferenceId());
        }
    }
}
