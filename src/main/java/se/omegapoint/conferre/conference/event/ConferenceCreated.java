package se.omegapoint.conferre.conference.event;

import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.conference.domain.Conference;
import se.omegapoint.conferre.event.Event;

public class ConferenceCreated {

	private final Identity conferenceId;
	private final String name;

	private ConferenceCreated(Conference conference) {
		this.name = conference.getName();
		this.conferenceId = conference.getId();
	}

	public Identity getConferenceId() {
		return conferenceId;
	}

	public String getName() {
		return name;
	}

	public static ConferenceCreated from(Conference conference) {
		return new ConferenceCreated(conference);
	}

	public Event asEvent() {
		return new Event("CREATED", this);
	}

}
