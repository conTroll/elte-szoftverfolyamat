package hu.szoftverfolyamat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BaseController {

    @ExceptionHandler(Exception.class)
    public RedirectView handleServiceException(final Exception e) {
        return new RedirectView("/", true);
    }
}
