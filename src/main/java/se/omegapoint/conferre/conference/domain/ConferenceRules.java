package se.omegapoint.conferre.conference.domain;

import org.springframework.stereotype.Component;

@Component
public class ConferenceRules {
	public void validate(ConferenceStore store, Conference conference) {
		store.forEach((id, existing) -> conference.requireGood(existing));
	}
}
