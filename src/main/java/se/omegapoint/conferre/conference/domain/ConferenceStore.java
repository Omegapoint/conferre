package se.omegapoint.conferre.conference.domain;

import org.springframework.stereotype.Component;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
public class ConferenceStore {

	private final Map<Identity, Conference> store = new HashMap<>();

	void forEach(BiConsumer<Identity, Conference> requireGood) {
		store.forEach(requireGood);
	}

	public Conference applyEvent(Event event) {
		switch (event.getType()) {
			case "CREATED":
				return created(event);
			default:
				throw new UnsupportedOperationException("Unhandled event type: " + event.getType());
		}
	}

	private Conference created(Event event) {
		Conference conference = (Conference) event.getPayload();
		store.put(conference.getId(), conference);
		return conference;
	}

	public List<Conference> listConferences() {
		return new ArrayList<>(store.values());
	}
}
