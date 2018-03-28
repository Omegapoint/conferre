package se.omegapoint.conferre.conference.domain;

import se.omegapoint.conferre.Entity;
import se.omegapoint.conferre.Identity;
import se.omegapoint.conferre.conference.event.ConferenceCreated;

public class Conference extends Entity {

	private final String name;

	public Conference(String name) {
		this(Identity.instance(), name);
	}

	private Conference(Identity conferenceId, String name) {
		super(conferenceId);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	void requireGood(Conference existing) {
		if (name.equals(existing.name)) {
			throw new IllegalStateException("Conflicting conference: " + name);
		}
	}

	static Conference fromEvent(ConferenceCreated conferenceCreated) {
		return new Conference(conferenceCreated.getConferenceId(), conferenceCreated.getName());
	}
}
