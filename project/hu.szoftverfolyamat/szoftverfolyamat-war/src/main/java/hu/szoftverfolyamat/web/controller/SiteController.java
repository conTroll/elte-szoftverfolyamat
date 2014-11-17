package hu.szoftverfolyamat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteController {

	private static final String JSP_NAME = "site";

	@RequestMapping(value = "/" + SiteController.JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet() {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(SiteController.JSP_NAME);
		return modelAndView;
	}
}
