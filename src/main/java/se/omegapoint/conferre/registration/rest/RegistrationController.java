package se.omegapoint.conferre.registration.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.omegapoint.conferre.registration.domain.Registration;
import se.omegapoint.conferre.registration.service.RegistrationService;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Registration createRegistration(Registration registration) {
        registrationService.createRegistration(registration);
        return registration;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Registration> listRegistrations() {
        return registrationService.listRegistrations();
    }
}
