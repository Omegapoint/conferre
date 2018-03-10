package se.omegapoint.conferre.registration.domain;

import org.springframework.stereotype.Component;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
public class RegistrationStore {

    private final Map<Identity, Registration> store = new HashMap<>();

    public void forEach(BiConsumer<Identity, Registration> mapFunction) {
        store.forEach(mapFunction);
    }

    public Registration applyEvent(Event event) {
        switch (event.getType()) {
            case "CREATED":
                return created(event);
            default:
                throw new UnsupportedOperationException("Unsupported event type: " + event.getType());
        }
    }

    private Registration created(Event event) {
        Registration registration = (Registration) event.getPayload();
        store.put(registration.getId(), registration);
        return registration;
    }

    public List<Registration> listRegistrations() {
        return new ArrayList<>(store.values());
    }
}
