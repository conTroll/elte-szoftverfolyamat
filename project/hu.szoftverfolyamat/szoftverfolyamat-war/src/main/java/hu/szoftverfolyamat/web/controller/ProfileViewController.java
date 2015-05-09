package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.UserProfileDataService;
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
public class ProfileViewController {

	@Autowired
	private UserProfileDataService userProfileDAtaService;
	
	@RequestMapping(URI.PROFILE_VIEW)
	public ModelAndView show(final Principal principal, @RequestParam("userId") Long userId) {
		ModelAndView modelAndView;
		
		modelAndView = new ModelAndView(Template.PROFILE_VIEW);
		modelAndView.addObject("userProfileDataDto", this.userProfileDAtaService.findByUserCredentialId(userId));
		return modelAndView;
	}
}
