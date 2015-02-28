package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.service.UserConnectionService;
import hu.szoftverfolyamat.service.UserProfileDataService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.requestobject.IdRequest;
import hu.szoftverfolyamat.web.requestobject.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@Secured({ Role.USER, Role.ADMIN })
public class ContactsController extends BaseController {

	@Autowired
	private UserProfileDataService userProfileDataService;

	@Autowired
	private UserConnectionService userConnectionService;

    @RequestMapping(URI.CONTACTS_SHOW)
    public ModelAndView show(final Principal principal) {
        final ModelAndView result = new ModelAndView(Template.CONTACTS_SHOW);
        result.addObject("contactList", userProfileDataService.getFriendsByUserId(getCurrentUser(principal)));
        return result;
    }

	@RequestMapping(value = URI.CONTACTS_ADD, method = RequestMethod.POST)
	public RedirectView addContact(final Principal principal, final @RequestBody IdRequest request) {
        userConnectionService.createUserConnection(getCurrentUser(principal), request.getId());
        return new RedirectView(URI.CONTACTS_SEARCH);
	}

	@RequestMapping(value = URI.CONTACTS_DELETE, method = RequestMethod.POST)
	public RedirectView deleteContact(final Principal principal, final @RequestBody IdRequest request) {
        userConnectionService.deleteUserConnection(getCurrentUser(principal), request.getId());
        return new RedirectView(URI.CONTACTS_SEARCH);
	}

    @RequestMapping(value = URI.CONTACTS_SEARCH, method = RequestMethod.GET)
    public String showSearch() {
        return Template.CONTACTS_SEARCH;
    }

    @RequestMapping(value = URI.CONTACTS_SEARCH, method = RequestMethod.POST)
    public ModelAndView doSearch(final Principal principal, final @RequestBody SearchRequest request) {
        final List<UserProfileDataDto> profiles = userProfileDataService.searchUserProfileDataDtos(
                getCurrentUser(principal), request.getEmail(), request.getFullName(), request.getPlace(), request.getJob()
        );

        final ModelAndView result = new ModelAndView(Template.CONTACTS_SEARCH);
        result.addObject("contactList", profiles);
        return result;
    }
}
