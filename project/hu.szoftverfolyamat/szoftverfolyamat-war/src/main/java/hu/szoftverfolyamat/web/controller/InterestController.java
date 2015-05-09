package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.InterestService;
import hu.szoftverfolyamat.web.helper.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(URI.INTEREST)
public class InterestController {

    @Autowired
    private InterestService interestService;

    public void showAll() {

    }

    public void showMostViewed() {

    }

    public void doAddToUser() {

    }

    public void doRemoveFromUser() {

    }

    public void doAddToChannel() {

    }

    public void doRemoveFromChannel() {

    }
}
