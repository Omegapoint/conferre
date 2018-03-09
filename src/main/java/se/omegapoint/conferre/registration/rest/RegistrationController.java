package se.omegapoint.conferre.registration.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.omegapoint.conferre.registration.domain.Registration;
import se.omegapoint.conferre.registration.domain.RegistrationRepository;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Registration createRegistration(Registration registration) {
        registrationRepository.createRegistration(registration);
        return registration;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Registration> listRegistrations() {
        return registrationRepository.listRegistrations();
    }
}
