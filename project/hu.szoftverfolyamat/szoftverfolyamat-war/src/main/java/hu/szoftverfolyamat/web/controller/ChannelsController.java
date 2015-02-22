package hu.szoftverfolyamat.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChannelsController {

	private static final String JSP_NAME = "browseChannels";
    private static final String MY_CHANNELS = "myChannels";

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet() {
		return new ModelAndView(JSP_NAME);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + MY_CHANNELS, method = RequestMethod.GET)
	public ModelAndView search() {
		return new ModelAndView(MY_CHANNELS);
	}

}
