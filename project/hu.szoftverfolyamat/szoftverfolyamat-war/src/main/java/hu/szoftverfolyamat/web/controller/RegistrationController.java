package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.UserCredentialDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.service.UserCredentialService;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;

import javax.validation.Valid;

import lombok.NonNull;

@Controller
@RequestMapping(value = URI.USER_REGISTRATION)
public class RegistrationController extends BaseController {


	@Autowired
	private UserCredentialService userCredentialService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
        final UserCredentialDto userCredentialDto = new UserCredentialDto();
		userCredentialDto.setUserProfileDataDto(new UserProfileDataDto());

        final  ModelAndView result = new ModelAndView(Template.USER_REGISTRATION);
		result.addObject("userCredentialDto", userCredentialDto);
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doRegistration(@Valid @ModelAttribute(value = "userCredentialDto") UserCredentialDto userCredentialDto,
			@NonNull final BindingResult bindingResult)  throws ParseException {
		ModelAndView modelAndView;

		
		if (bindingResult.hasErrors()) {
			 
		    return new ModelAndView(Template.USER_REGISTRATION);
        }
		
		try {
			userCredentialService.createUserCredential(userCredentialDto);
            // TODO RedirectView instead
			modelAndView = new ModelAndView("redirect:" + URI.USER_LOGIN);
		} catch (UserServiceException e) {
			modelAndView = new ModelAndView(Template.USER_REGISTRATION);
			// TODO hibakódok és hibaüzenetek bevezetése
			modelAndView.addObject("error", "error");
		}

		return modelAndView;
	}
}
