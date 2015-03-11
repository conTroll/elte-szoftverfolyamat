package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.UserCredentialDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.service.UserCredentialService;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
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

	// TODO ideiglenes megoldás, mert a spring mvc formja nem tud egymásba
	// ágyazott objektumot kezelni?? COnverter írással elvileg megoldható
	// private UserCredentialDto convertRegistrationDtoToUserCredentialDto(
	// RegistrationFormDto registrationFormDto) {
	// UserCredentialDto userCredentialDto;
	// UserProfileDataDto userProfileDataDto;
	//
	// userProfileDataDto = new UserProfileDataDto();
	// userProfileDataDto.setBirthday(registrationFormDto.getBirthday());
	// userProfileDataDto.setEmail(registrationFormDto.getEmail());
	// userProfileDataDto.setFullName(registrationFormDto.getFullName());
	// userProfileDataDto.setHabitat(registrationFormDto.getHabitat());
	// userProfileDataDto.setJob(registrationFormDto.getJob());
	// userProfileDataDto.setPublicBirthday(registrationFormDto
	// .getPublicBirthday());
	// userProfileDataDto.setPublicHabitat(registrationFormDto
	// .getPublicHabitat());
	// userProfileDataDto.setPublicJobAndWorkplace(registrationFormDto
	// .getPublicJobAndWorkplace());
	// userProfileDataDto.setShortName(registrationFormDto.getShortName());
	// userProfileDataDto.setWorkplace(registrationFormDto.getWorkplace());
	//
	// userCredentialDto = new UserCredentialDto();
	// userCredentialDto.setPassword(registrationFormDto.getPassword());
	// userCredentialDto.setUsername(registrationFormDto.getUsername());
	// userCredentialDto.setUserProfileDataDto(userProfileDataDto);
	// return userCredentialDto;
	// }

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
        final UserCredentialDto userCredentialDto = new UserCredentialDto();
		userCredentialDto.setUserProfileDataDto(new UserProfileDataDto());

        final  ModelAndView result = new ModelAndView(Template.USER_REGISTRATION);
		result.addObject("userCredentialDto", userCredentialDto);
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doRegistration(@ModelAttribute(value = "userCredentialDto") UserCredentialDto userCredentialDto)  throws ParseException {
		ModelAndView modelAndView;

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
