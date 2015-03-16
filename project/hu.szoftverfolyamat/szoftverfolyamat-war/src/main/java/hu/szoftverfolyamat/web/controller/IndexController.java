package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.MessageService;
import hu.szoftverfolyamat.service.PostService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@Secured({ Role.USER, Role.ADMIN })
public class IndexController extends BaseController {

    @Autowired
    private PostService postService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = URI.INDEX)
    public ModelAndView handleGet(final Principal principal, @RequestParam(value = "successfulChannelCreation", required = false) Boolean successfulChannel) {
        final Long userId = getCurrentUser(principal);
        final ModelAndView result = new ModelAndView(Template.INDEX);
        result.addObject("username", principal.getName());
        result.addObject("currentUserId", userId);
        result.addObject("postList", postService.getPostsForUser(userId));
        result.addObject("numberOfMessages", messageService.getNumberOfNonViewedMessages(userId));
        result.addObject("successfulChannelCreation", successfulChannel);
        return result;
    }
}
