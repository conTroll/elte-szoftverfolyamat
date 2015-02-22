package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.PostService;
import hu.szoftverfolyamat.service.UserCredentialService;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteController {

	private static final String JSP_NAME = "site";

	@Autowired
	private PostService postService;

	@Autowired
	private UserCredentialService userCredentialService;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet(final Principal principal) {
        // RedirectView (to the post list) instead
        final ModelAndView result = new ModelAndView(JSP_NAME);
		result.addObject("username", principal.getName());
		result.addObject("currentUserId", userCredentialService.getUser(principal.getName()).getCredentialId());
		result.addObject("postList", postService.getPostsForUser(userCredentialService.getUser(principal.getName()).getCredentialId()));
		return result;
	}
}
