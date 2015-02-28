package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {

	@RequestMapping(URI.USER_LOGIN)
	public ModelAndView handleGet(
            final @RequestParam(value = "error", required = false) String error,
			final @RequestParam(value = "logout", required = false) String logout) {
		final ModelAndView result = new ModelAndView(Template.USER_LOGIN);

		if (error != null) {
			// TODO szerver oldali hibaüzenet kezelése a bejelentkezésnél
			result.addObject("error", "error");
		}
		return result;
	}
}
