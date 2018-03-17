package se.omegapoint.conferre.proposal.domain.supportive;

import se.omegapoint.conferre.Identity;

class ConferenceBuilder {

	private final Identity conferenceId;

	private ConferenceBuilder(Identity conferenceId) {
		this.conferenceId = conferenceId;

	}

	static ConferenceBuilder aConference(Identity conferenceId) {
		return new ConferenceBuilder(conferenceId);
	}

	Conference build() {
		return new Conference(conferenceId);
	}
}
