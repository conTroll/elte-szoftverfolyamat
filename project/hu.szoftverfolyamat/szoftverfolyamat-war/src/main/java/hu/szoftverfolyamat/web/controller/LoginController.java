package hu.szoftverfolyamat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private static final String JSP_NAME = "login";

	@RequestMapping(value = "/" + LoginController.JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(LoginController.JSP_NAME);
		if(error != null) {
			//TODO szerver oldali hibaüzenet kezelése a bejelentkezésnél
			modelAndView.addObject("error", "error");
		}
		return modelAndView;
	}
}
