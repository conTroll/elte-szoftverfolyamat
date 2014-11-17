package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.UserCredentialDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.service.UserCredentialService;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

	public static final String JSP_NAME = "registration";

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

	@RequestMapping(value = "/" + RegistrationController.JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet() {
		ModelAndView modelAndView;
		UserCredentialDto userCredentialDto;

		userCredentialDto = new UserCredentialDto();
		userCredentialDto.setUserProfileDataDto(new UserProfileDataDto());

		modelAndView = new ModelAndView(RegistrationController.JSP_NAME);
		modelAndView.addObject("userCredentialDto", userCredentialDto);
		return modelAndView;
	}

	@RequestMapping(value = "/" + RegistrationController.JSP_NAME, method = RequestMethod.POST)
	public ModelAndView register(
			@ModelAttribute(value = "userCredentialDto") UserCredentialDto userCredentialDto)
			throws ParseException {
		ModelAndView modelAndView;

		try {
			this.userCredentialService.createUserCredential(userCredentialDto);
			modelAndView = new ModelAndView("redirect:"
					+ LoginController.JSP_NAME);
		} catch (UserServiceException e) {
			modelAndView = new ModelAndView(RegistrationController.JSP_NAME);
			// TODO hibakódok és hibaüzenetek bevezetése
			modelAndView.addObject("error", "error");
		}

		return modelAndView;
	}
}
