package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.UserConnectionService;
import hu.szoftverfolyamat.service.UserProfileDataService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;

@Controller
@Secured({ Role.USER, Role.ADMIN })
public class ContactsController extends BaseController {

    // TODO a SpringMVC tamogatja, hogy o feloldja a JSON-t, majd megnezem, hogy csinaltuk

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
	public RedirectView addContact(final Principal principal, final @RequestBody String text) {
		if ((text != null) && text.startsWith("id=")) {
            final String postContent = text.replace("id=contact", "");

			if (!postContent.isEmpty()) {
				userConnectionService.createUserConnection(getCurrentUser(principal), Long.parseLong(postContent));
			}
		}

        return new RedirectView(URI.CONTACTS_SEARCH);
	}

	@RequestMapping(value = URI.CONTACTS_DELETE, method = RequestMethod.POST)
	public RedirectView deleteContact(final Principal principal, final @RequestBody String text) {
		if ((text != null) && text.startsWith("id=")) {
			final String postContent = text.replace("id=contact", "");

			if (!postContent.isEmpty()) {
				userConnectionService.deleteUserConnection(getCurrentUser(principal), Long.parseLong(postContent));
			}
		}

        return new RedirectView(URI.CONTACTS_SEARCH);
	}

    @RequestMapping(value = URI.CONTACTS_SEARCH, method = RequestMethod.GET)
    public String showSearch() {
        return Template.CONTACTS_SEARCH;
    }

	@RequestMapping(value = URI.CONTACTS_SEARCH, method = RequestMethod.POST)
	public ModelAndView doSearch(final Principal principal, final @RequestBody String text) throws JSONException, UnsupportedEncodingException {
        final JSONObject jsonObject = new JSONObject(URLDecoder.decode(text, "UTF-8"));
        final ModelAndView result = new ModelAndView(Template.CONTACTS_SEARCH);
		result.addObject("contactList", userProfileDataService.searchUserProfileDataDtos(
                getCurrentUser(principal),
                jsonObject.getString("email"),
                jsonObject.getString("fullName"),
                jsonObject.getString("place"),
                jsonObject.getString("job")
        ));

		return result;
	}
}
