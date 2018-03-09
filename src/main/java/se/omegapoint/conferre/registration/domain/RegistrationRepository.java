package se.omegapoint.conferre.registration.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.omegapoint.conferre.Entity;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.EventBus;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RegistrationRepository {

    @Autowired
    private EventBus eventBus;

    private Map<Identity,Registration> currentState = new HashMap<>();
    private final Set<String> eMails = new HashSet<>();

    public RegistrationRepository() {
        createCurrentState();
    }

    public List<Registration> listRegistrations() {
        return new ArrayList<>(currentState.values());
    }

    private void createCurrentState() {
        currentState = eventBus.list("registration").stream()
                .map(e -> (Registration) e.getData())
                .collect(Collectors.toMap(Entity::getId, r -> r));
    }
}
