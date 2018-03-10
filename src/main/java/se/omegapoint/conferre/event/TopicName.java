package se.omegapoint.conferre.event;

import java.util.Arrays;
import java.util.List;

public enum TopicName {
	REGISTRATION,
	CONFERENCE,
	PROPOSAL;

	static List<TopicName> asList() {
		return Arrays.asList(values());
	}
}
