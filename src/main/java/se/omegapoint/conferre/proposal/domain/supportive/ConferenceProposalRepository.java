package se.omegapoint.conferre.proposal.domain.supportive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.conference.event.ConferenceCreated;
import se.omegapoint.conferre.event.Event;
import se.omegapoint.conferre.event.EventBus;
import se.omegapoint.conferre.event.EventListener;
import se.omegapoint.conferre.event.TopicName;

import java.util.HashSet;
import java.util.Set;

import static se.omegapoint.conferre.event.TopicName.CONFERENCE;

@Repository
public class ConferenceProposalRepository implements EventListener {
	private EventBus eventBus;

	private Set<Identity> currentState = new HashSet<>();

	@Autowired
	public ConferenceProposalRepository(EventBus eventBus) {
		this.eventBus = eventBus;
		createCurrentState();
		eventBus.registerListener(this);
	}

	private void createCurrentState() {
		eventBus.list(CONFERENCE).forEach(this::applyEvent);
	}

	public void onNext(Event event) {
		System.out.println("Proposal got a " + event);
		applyEvent(event);
	}

	private void applyEvent(Event event) {
		Conference conference = ConferenceBuilder.aConference(((ConferenceCreated) event.getPayload()).getConferenceId()).build();
		currentState.add(conference.getId());
	}

	public boolean exists(Identity conferenceId) {
		return currentState.contains(conferenceId);
	}

	@Override
	public TopicName getTopicName() {
		return CONFERENCE;
	}

}
