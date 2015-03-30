package hu.szoftverfolyamat.web.controller;

import java.security.Principal;
import java.text.ParseException;

import javax.validation.Valid;

import lombok.NonNull;
import hu.szoftverfolyamat.dto.UserCredentialDto;
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

@Controller
@RequestMapping(value = URI.USER_PROFILE)
public class SettingsController extends BaseController {
	
	@Autowired
	private UserCredentialService userCredentialService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView show(Principal principal) {
		Long credentialId;
		ModelAndView modelAndView;
		
		credentialId = this.getCurrentUser(principal);
		modelAndView = new ModelAndView(Template.USER_PROFILE);
		modelAndView.addObject("userCredentialDto", userCredentialService.getUserCredentialById(credentialId));
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute(value = "userCredentialDto") UserCredentialDto userCredentialDto,
			@NonNull final BindingResult bindingResult) {
		ModelAndView modelAndView;
		
		if (bindingResult.hasErrors()) {
			 
		    return new ModelAndView("redirect:" + Template.INDEX);
        }
		
		try {
			this.userCredentialService.updateUserCredential(userCredentialDto);
		} catch (ParseException | UserServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		modelAndView = new ModelAndView("redirect:" + Template.INDEX);
		return modelAndView;
	}
}
