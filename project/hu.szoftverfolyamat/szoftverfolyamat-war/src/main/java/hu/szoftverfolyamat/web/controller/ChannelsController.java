package hu.szoftverfolyamat.web.controller;

import java.security.Principal;

import hu.szoftverfolyamat.exception.ChannelServiceException;
import hu.szoftverfolyamat.service.ChannelService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.requestobject.CreateChannelRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Secured({ Role.USER, Role.ADMIN })
@RequestMapping(URI.CHANNELS)
public class ChannelsController extends BaseController {
	
	@Autowired
	private ChannelService service;

	@RequestMapping(URI.SHOW_ALL)
	public String showChannels() {
		return Template.CHANNELS_BROWSER;
	}

	@RequestMapping(URI.SHOW_OWN)
	public String showMine() {
        return Template.CHANNELS_MINE;
	}
	
	@RequestMapping(value = URI.CREATE, method = RequestMethod.GET)
	public ModelAndView showCreateForm() {
		CreateChannelRequest request = new CreateChannelRequest();
		ModelAndView result = new ModelAndView(Template.CREATE_CHANNEL);
		result.addObject("createChannelRequest", request);
		return result;
	}
	
	@RequestMapping(value = URI.CREATE, method = RequestMethod.POST)
	public ModelAndView doCreateChannel(final Principal principal, @ModelAttribute("createChannelRequest") CreateChannelRequest request) {
		Long userId = this.getCurrentUser(principal);
		ModelAndView result;
		
		try {
			this.service.createChannel(userId, request.getName(), request.getDescription(), request.isOpen());
			result = new ModelAndView("redirect:/");
			result.addObject("successfulChannelCreation", true);
		} catch (ChannelServiceException e) {
			result = new ModelAndView(Template.CREATE_CHANNEL);
			result.addObject("error", e.getMessage());
		}
		
		return result;
	}
	
}
