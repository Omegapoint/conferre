package se.omegapoint.conferre.conference.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.omegapoint.conferre.conference.domain.Conference;
import se.omegapoint.conferre.conference.service.ConferenceService;

import java.util.List;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    private ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Conference createConference(Conference conference) {
        conferenceService.createConference(conference);
        return conference;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Conference> listConferences() {
        return conferenceService.listConferences();
    }


}
