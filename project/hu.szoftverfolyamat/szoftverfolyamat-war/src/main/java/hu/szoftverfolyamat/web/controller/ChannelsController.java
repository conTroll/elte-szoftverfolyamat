package hu.szoftverfolyamat.web.controller;

import java.security.Principal;
import java.util.List;

import hu.szoftverfolyamat.dto.ChannelProfileDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.exception.ChannelServiceException;
import hu.szoftverfolyamat.service.ChannelService;
import hu.szoftverfolyamat.service.UserProfileDataService;
import hu.szoftverfolyamat.service.mapper.UserProfileDataMapper;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.requestobject.CreateChannelRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Secured({ Role.USER, Role.ADMIN })
@RequestMapping(URI.CHANNELS)
public class ChannelsController extends BaseController {

	@Autowired
	private ChannelService service;
	
	@Autowired
	private UserProfileDataService userService;
	
	@Autowired
	private UserProfileDataMapper userMapperService;

	@RequestMapping(URI.SHOW_ALL)
	public String showChannels() {
		return Template.CHANNELS_BROWSER;
	}

	@RequestMapping(URI.SHOW_OWN)
	public ModelAndView showMine(final Principal principal) {
		ModelAndView result;
		try {
			Long userId = this.getCurrentUser(principal);
			List<ChannelProfileDto> channels = this.service.getChannelsOwnedByUser(userId);
			UserProfileDataDto userProfile = this.userMapperService.apply(this.userService.findByUserCredentialId(userId));
			result = new ModelAndView(Template.CHANNELS_MINE);
			result.addObject("channels", channels);
			result.addObject("userProfile", userProfile);
			return result;
		} catch (ChannelServiceException e) {
			// TODO error handling
			return new ModelAndView(Template.INDEX);
		}
	}

	@RequestMapping(value = URI.CREATE, method = RequestMethod.GET)
	public ModelAndView showCreateForm(final Model model, @ModelAttribute("error") String error) {
		CreateChannelRequest request = new CreateChannelRequest();
		ModelAndView result = new ModelAndView(Template.CREATE_CHANNEL);
		result.addObject("createChannelRequest", request);
		result.addObject("error", error);
		return result;
	}

	@RequestMapping(value = URI.CREATE, method = RequestMethod.POST)
	public RedirectView doCreateChannel(final Principal principal, @ModelAttribute("createChannelRequest") CreateChannelRequest request, final RedirectAttributes attributes) {
		Long userId = this.getCurrentUser(principal);
		RedirectView result;
		try {
			this.service.createChannel(userId, request.getName(), request.getDescription(), request.isOpen());
			attributes.addFlashAttribute("successfulChannelCreation", true);
			result = new RedirectView("/", true);
			return result;
		} catch (ChannelServiceException e) {
			attributes.addFlashAttribute("error", e.getMessage());
			result = new RedirectView(URI.CHANNELS + URI.CREATE, true);
			return result;
		}
	}

}