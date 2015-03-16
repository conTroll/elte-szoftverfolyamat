package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class BaseController {

    @Autowired
    private UserCredentialService userCredentialService;

    protected Long getCurrentUser(final Principal principal) {
        return userCredentialService.getUser(principal.getName()).getCredentialId();
    }
}
