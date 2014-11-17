package hu.szoftverfolyamat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

	private static final String JSP_NAME = "registration";

	@RequestMapping(value = "/" + RegistrationController.JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet() {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(RegistrationController.JSP_NAME);
		return modelAndView;
	}
}
