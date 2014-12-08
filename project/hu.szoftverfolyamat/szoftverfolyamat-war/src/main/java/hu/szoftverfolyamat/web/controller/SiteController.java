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

	public static final String JSP_NAME = "site";

	@Autowired
	private PostService postService;

	@Autowired
	private UserCredentialService userCredentialService;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + SiteController.JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet(Principal principal) {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(SiteController.JSP_NAME);
		modelAndView.addObject("username", principal.getName());
		modelAndView.addObject("currentUserId", this.userCredentialService
				.getUser(principal.getName()).getCredentialId());
		modelAndView.addObject("postList", this.postService
				.getPostsForUser(this.userCredentialService.getUser(
						principal.getName()).getCredentialId()));
		return modelAndView;
	}
}
