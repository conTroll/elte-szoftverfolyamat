package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.UserCredentialService;
import hu.szoftverfolyamat.web.helper.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class BaseController {

    @Autowired
    private UserCredentialService userCredentialService;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RedirectView handleServiceException(final Exception exception) throws Exception {
        if (exception.getClass().equals(AccessDeniedException.class)) {
            throw exception;
        }

        // TODO error message
        return new RedirectView(URI.INDEX, true);

// FOR DEBUG PURPOSES
//        String result = exception.getClass().getName() + "\n" + exception.getMessage() + "\n" + exception.getCause() + "\n";
//
//
//        for (StackTraceElement ste : exception.getStackTrace()) {
//            result += ste.toString() + "\n";
//        }
//
//        return result;
    }

    protected Long getCurrentUser(final Principal principal) {
        return userCredentialService.getUser(principal.getName()).getCredentialId();
    }
}
