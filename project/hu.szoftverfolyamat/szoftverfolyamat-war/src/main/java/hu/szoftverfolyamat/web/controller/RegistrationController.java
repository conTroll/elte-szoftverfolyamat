package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.UserCredentialDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.service.UserCredentialService;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.parser.ProfileRequestMapper;
import hu.szoftverfolyamat.web.requestobject.ProfileFormRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
@RequestMapping(value = URI.USER_REGISTRATION)
public class RegistrationController extends BaseController {


	@Autowired
	private UserCredentialService userCredentialService;
	
	@Autowired
	private ProfileRequestMapper profileRequestMapper;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		ProfileFormRequest profileFormRequest = new ProfileFormRequest();

        final  ModelAndView result = new ModelAndView(Template.USER_REGISTRATION);
		result.addObject("profileFormRequest", profileFormRequest);
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doRegistration(@ModelAttribute(value = "profileFormRequest") ProfileFormRequest profileFormRequest) {
		ModelAndView modelAndView;

		try {
			userCredentialService.createUserCredential(profileRequestMapper.decode(profileFormRequest));
            // TODO RedirectView instead
			modelAndView = new ModelAndView("redirect:" + URI.USER_LOGIN);
		} catch (UserServiceException | ParseException e) {
			modelAndView = new ModelAndView(Template.USER_REGISTRATION);
			// TODO hibakódok és hibaüzenetek bevezetése
			modelAndView.addObject("error", "error");
		}

		return modelAndView;
	}
}
