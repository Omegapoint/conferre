package se.omegapoint.conferre.conference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.omegapoint.conferre.conference.domain.Conference;
import se.omegapoint.conferre.conference.domain.ConferenceRules;
import se.omegapoint.conferre.conference.domain.ConferenceStore;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;

import java.util.List;

import static se.omegapoint.conferre.event.TopicName.CONFERENCE;

@Service
public class ConferenceService {
	private final ConferenceStore store;
	private ConferenceRules rules;
	private EventBus eventBus;

	@Autowired
	public ConferenceService(ConferenceStore store, ConferenceRules rules, EventBus eventBus) {
		this.store = store;
		this.rules = rules;
		this.eventBus = eventBus;
	}

	public synchronized Conference createConference(Conference conference) {
		rules.validate(store, conference);
		Event event = new Event("CREATED", conference);
		return publish(event);
	}

	private Conference publish(Event event) {
		eventBus.publish(CONFERENCE, event);
		return store.applyEvent(event);
	}

	public List<Conference> listConferences() {
		return store.listConferences();
	}

}
