package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured({ Role.USER, Role.ADMIN })
public class ChannelsController {

	@RequestMapping(URI.CHANNELS_BROWSE)
	public String showChannels() {
		return Template.CHANNELS_BROWSER;
	}

	@RequestMapping(URI.CHANNELS_MINE)
	public String showMine() {
        return Template.CHANNELS_MINE;
	}
}
