package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.InterestDto;
import hu.szoftverfolyamat.service.InterestService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(URI.INTEREST)
@Secured({ Role.USER, Role.ADMIN })
public class InterestController extends BaseController {

    @Autowired
    private InterestService interestService;

    @RequestMapping(value = URI.SHOW_ALL, method = RequestMethod.GET)
    @ResponseBody
    public List<InterestDto> showAll(final @RequestParam(value = "prefix", required = false) String prefix) {
        if (prefix == null) {
            return interestService.getAll();
        } else {
            return interestService.getWithPrefix(prefix);
        }
    }

    @RequestMapping(value = URI.ADD)
    @ResponseBody
    public InterestDto addToCurrentUser(final Principal principal, final @RequestParam(value = "interest") String interest) {
        long userId = getCurrentUser(principal);
        return interestService.addToUser(userId, interest.toLowerCase());
    }

    @RequestMapping(value = URI.DELETE)
    @ResponseBody
    public String removeFromCurrentUser(final Principal principal, final @RequestParam(value = "interest") String interest) {
        long userId = getCurrentUser(principal);
        interestService.removeFromUser(userId, interest.toLowerCase());
        return "OK";
    }

    @RequestMapping(value = URI.CHANNELS + "/" + URI.ADD)
    @ResponseBody
    public InterestDto addToChannel(final @RequestParam(value = "channel") long channelId, final @RequestParam(value = "interest") String interest) {
        return interestService.addToChannel(channelId, interest.toLowerCase());
    }

    @RequestMapping(value = URI.CHANNELS + "/" + URI.DELETE)
    @ResponseBody
    public String removeFromChannel(final @RequestParam(value = "channel") long channelId, final @RequestParam(value = "interest") String interest) {
        interestService.removeFromChannel(channelId, interest.toLowerCase());
        return "OK";
    }

    @RequestMapping
    public ModelAndView showDashboard() {
        final ModelAndView result = new ModelAndView(Template.INTEREST_DASHBOARD);
        result.addObject("interests", interestService.getAll());
        return result;
    }

    @RequestMapping(value = URI.SHOW_BY_ID)
    public ModelAndView showInterest(@PathVariable("id") final Long interestId) {
        final ModelAndView result = new ModelAndView(Template.INTEREST_PAGE);
        result.addObject("interest", interestService.getById(interestId));
        return result;
    }
}
