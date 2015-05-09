package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.UserCredentialDto;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.service.UserCredentialService;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.parser.ProfileRequestMapper;
import hu.szoftverfolyamat.web.requestobject.ProfileFormRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;

@Controller
@RequestMapping(value = URI.USER_PROFILE)
public class SettingsController extends BaseController {
	
	@Autowired
	private UserCredentialService userCredentialService;
	
	@Autowired
	private ProfileRequestMapper profileRequestMapper;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView show(Principal principal) {
		Long credentialId;
		ModelAndView modelAndView;
		UserCredentialDto credentialDto;
		
		credentialId = this.getCurrentUser(principal);
		credentialDto = userCredentialService.getUserCredentialById(credentialId);
		modelAndView = new ModelAndView(Template.USER_PROFILE);
		modelAndView.addObject("imageId", credentialDto.getUserProfileDataDto().getAvatarId());
		modelAndView.addObject("profileFormRequest", profileRequestMapper.encode(credentialDto));
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView update(Principal principal, @Valid @ModelAttribute(value = "profileFormRequest") ProfileFormRequest profileFormRequest,
			@NonNull final BindingResult bindingResult) {
		ModelAndView modelAndView;
		Long credentialId;
		
		if (bindingResult.hasErrors()) {
			
			modelAndView = new ModelAndView(Template.USER_PROFILE);
			modelAndView.addObject("error", bindingResult.getFieldErrors().toString());
			return modelAndView;
		}
		
		try {
			this.userCredentialService.updateUserCredential(profileRequestMapper.decode(profileFormRequest));
		} catch (ParseException | UserServiceException e) {
			credentialId = this.getCurrentUser(principal);
			modelAndView = new ModelAndView(Template.USER_PROFILE);
			modelAndView.addObject("profileFormRequest", profileRequestMapper.encode(userCredentialService.getUserCredentialById(credentialId)));
		}
		
		modelAndView = new ModelAndView("redirect:/");
		return modelAndView;
	}
}
