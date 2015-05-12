package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.ChannelProfileDto;
import hu.szoftverfolyamat.dto.InterestDto;
import hu.szoftverfolyamat.dto.InterestsQueryResultDto;
import hu.szoftverfolyamat.dto.InterestsQueryResultsDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.service.InterestService;
import hu.szoftverfolyamat.service.UserConnectionService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(URI.INTEREST)
@Secured({ Role.USER, Role.ADMIN })
public class InterestController extends BaseController {

	@Autowired
	private InterestService interestService;

	@RequestMapping(value = URI.SHOW_ALL, method = RequestMethod.GET)
    @ResponseBody
    public InterestsQueryResultsDto showAll(final @RequestParam(value = "prefix", required = false) String prefix) {
    	InterestsQueryResultsDto interestsQueryResultsDto;
    	InterestsQueryResultDto interestsQueryResultDto;
    	
    	// TODO gyors megoldás a query megejelenítéséhez a Semantic-ui miatt "elég csúnya, de nincs időm szépítgetni"
    	interestsQueryResultsDto = new InterestsQueryResultsDto();
    	interestsQueryResultsDto.setResults(new ArrayList<InterestsQueryResultDto>());
    	if (prefix == null) {
    		for(InterestDto interestDto : interestService.getAll()) {
    			interestsQueryResultDto = new InterestsQueryResultDto();
    			interestsQueryResultDto.setTitle(interestDto.getName());
    			interestsQueryResultDto.setDescription("Created at: " + interestDto.getCreatedAt().toString());
    			interestsQueryResultsDto.getResults().add(interestsQueryResultDto);
    		}
        } else {
        	for(InterestDto interestDto : interestService.getWithPrefix(prefix)) {
        		interestsQueryResultDto = new InterestsQueryResultDto();
        		interestsQueryResultDto.setTitle(interestDto.getName());
        		interestsQueryResultDto.setDescription("Created at: " + interestDto.getCreatedAt().toString());
    			interestsQueryResultsDto.getResults().add(interestsQueryResultDto);
    		}
        }
    	return interestsQueryResultsDto;
    }

	@RequestMapping(value = URI.ADD)
	@ResponseBody
	public InterestDto addToCurrentUser(final Principal principal,
			final @RequestParam(value = "interest") String interest) {
		long userId = getCurrentUser(principal);
		return interestService.addToUser(userId, interest.toLowerCase());
	}

	@RequestMapping(value = URI.DELETE)
	@ResponseBody
	public String removeFromCurrentUser(final Principal principal,
			final @RequestParam(value = "interest") String interest) {
		long userId = getCurrentUser(principal);
		interestService.removeFromUser(userId, interest.toLowerCase());
		return "OK";
	}

	@RequestMapping(value = URI.CHANNELS + "/" + URI.ADD)
	@ResponseBody
	public InterestDto addToChannel(
			final @RequestParam(value = "channel") long channelId,
			final @RequestParam(value = "interest") String interest) {
		return interestService.addToChannel(channelId, interest.toLowerCase());
	}

	@RequestMapping(value = URI.CHANNELS + "/" + URI.DELETE)
	@ResponseBody
	public String removeFromChannel(
			final @RequestParam(value = "channel") long channelId,
			final @RequestParam(value = "interest") String interest) {
		interestService.removeFromChannel(channelId, interest.toLowerCase());
		return "OK";
	}

	@RequestMapping
	public ModelAndView showDashboard() {
		final ModelAndView result = new ModelAndView(
				Template.INTEREST_DASHBOARD);
		result.addObject("interests", interestService.getAll());
		return result;
	}

	@RequestMapping(value = URI.SHOW_BY_ID)
	public ModelAndView showInterest(Principal principal ,@PathVariable("id") final Long interestId) {
		final long currentUserId = this.getCurrentUser(principal);
		final ModelAndView result = new ModelAndView(Template.INTEREST_PAGE);
		result.addObject("interest", interestService.getById(interestId));
		final List<UserProfileDataDto> users = interestService
				.getUsersForInterest(currentUserId, interestId);
		final List<ChannelProfileDto> channels = interestService
				.getChannelsForInterest(interestId);

		result.addObject("users", users.subList(0, Math.min(10, users.size())));
		result.addObject("channels",
				channels.subList(0, Math.min(10, channels.size())));
		result.addObject("currentUserId", currentUserId);
		return result;
	}
}
