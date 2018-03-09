package se.omegapoint.conferre.conference.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.omegapoint.conferre.conference.domain.Conference;
import se.omegapoint.conferre.conference.domain.ConferenceRepository;
import se.omegapoint.conferre.event.EventBus;

import java.util.List;

@RestController
@RequestMapping("/conference")
public class ConferenceController {


    @Autowired
    private EventBus eventBus;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Conference createConference(Conference conference) {
        eventBus.publish("conference", conference.asCreatedEvent());
        return conference;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Conference> listRegistrations() {
        return conferenceRepository.listConferences();
    }


}
