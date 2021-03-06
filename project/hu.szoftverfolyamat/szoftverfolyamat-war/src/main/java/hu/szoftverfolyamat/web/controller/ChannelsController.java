package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.ChannelProfileDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.ChannelProfileEntity;
import hu.szoftverfolyamat.enums.MatchType;
import hu.szoftverfolyamat.exception.ChannelServiceException;
import hu.szoftverfolyamat.repository.ChannelRepository;
import hu.szoftverfolyamat.service.ChannelService;
import hu.szoftverfolyamat.service.UserProfileDataService;
import hu.szoftverfolyamat.service.mapper.UserProfileDataMapper;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.requestobject.ChannelSearchRequest;
import hu.szoftverfolyamat.web.requestobject.CreateChannelRequest;
import lombok.NonNull;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@Secured({ Role.USER, Role.ADMIN })
@RequestMapping(URI.CHANNELS)
public class ChannelsController extends BaseController {
	
	@Value("/WEB-INF/resources/images/defaultChannelPicture.png")
	private Resource defaultPicture;

	@Autowired
	private ChannelService service;
	
	@Autowired
	private ChannelRepository repository;
	
	@Autowired
	private UserProfileDataService userService;
	
	@Autowired
	private UserProfileDataMapper userMapperService;

	@RequestMapping(URI.SEARCH)
	public String showSearchForm() {
		return Template.SEARCH_CHANNELS;
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
	
	@RequestMapping(value = URI.GET_IMAGE + "/{id}", method = RequestMethod.GET)
	public @ResponseBody byte[] getImage(@PathVariable("id") Long id) throws IOException {
		byte[] result = null;

		ChannelProfileEntity channel = this.repository.findOne(id);
		result = channel.getAvatar() == null ? null : channel.getAvatar().getImage();
		try {
			if (result == null) {
				result = IOUtils.toByteArray(defaultPicture.getInputStream());
			}
		} catch (IOException e) {
			// TODO error handling
		}
			
		return result;
	}
	
	@RequestMapping(value = URI.SHOW_SUBSCRIPTIONS, method = RequestMethod.GET)
	public ModelAndView showSubscriptions(final Principal principal) {
		ModelAndView result;
		try {
			Long userId = this.getCurrentUser(principal);
			List<ChannelProfileDto> activeSubs = this.service.getActiveSubscriptionsByUser(userId);
			List<ChannelProfileDto> pendingSubs = this.service.getPendingSubscriptionsByUser(userId);
			UserProfileDataDto userProfile = this.userMapperService.apply(this.userService.findByUserCredentialId(userId));
			result = new ModelAndView(Template.CHANNELS_SUBSCRIPTIONS);
			result.addObject("activeSubs", activeSubs);
			result.addObject("pendingSubs", pendingSubs);
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
	
	@RequestMapping(value = URI.SEARCH, method = RequestMethod.POST)
	public ModelAndView doSearch(Principal principal,@Valid @RequestBody ChannelSearchRequest request,
			@NonNull final BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			 
		    return new ModelAndView(Template.SEARCH_CHANNELS);
        }
		
		List<ChannelProfileDto> channels = this.service.searchByName(request.getSearchTerm(), MatchType.SUBSTRING);
		ModelAndView result = new ModelAndView(Template.SEARCH_CHANNELS);
		result.addObject("channels", channels);
		
		Long userId = this.getCurrentUser(principal);
		UserProfileDataDto userProfile = this.userMapperService.apply(this.userService.findByUserCredentialId(userId));
		result.addObject("userProfile", userProfile);
		
		return result;
		
	}

	@RequestMapping(value = URI.CREATE, method = RequestMethod.POST)
	public RedirectView doCreateChannel(final Principal principal,@Valid @ModelAttribute("createChannelRequest") CreateChannelRequest request,
			@NonNull final BindingResult bindingResult, final RedirectAttributes attributes) {
		Long userId = this.getCurrentUser(principal);
		RedirectView result;
		
		if (bindingResult.hasErrors()) {
			 
			attributes.addFlashAttribute("error", bindingResult.getFieldErrors().toString());
			return new RedirectView(URI.CHANNELS + URI.CREATE, true);
		}
		
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