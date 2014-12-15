package hu.szoftverfolyamat.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChannelsController {

	public static final String JSP_NAME = "browseChannels";
	public static final String MY_CHANNELS = "myChannels";

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + ChannelsController.JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet() {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(ChannelsController.JSP_NAME);
		return modelAndView;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + ChannelsController.MY_CHANNELS, method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(ChannelsController.MY_CHANNELS);
		return modelAndView;
	}

}
