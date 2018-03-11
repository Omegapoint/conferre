package se.omegapoint.conferre.registration.domain.supportive;

import se.omegapoint.conferre.Entity;
import se.omegapoint.conferre.conference.event.ConferenceCreated;

public class Conference extends Entity {

	public Conference(ConferenceCreated conferenceCreated) {
		super(conferenceCreated.getConferenceId());
	}
}