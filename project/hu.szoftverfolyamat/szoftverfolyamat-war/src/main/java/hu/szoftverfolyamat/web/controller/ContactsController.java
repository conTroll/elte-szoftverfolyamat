package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.UserConnectionService;
import hu.szoftverfolyamat.service.UserCredentialService;
import hu.szoftverfolyamat.service.UserProfileDataService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactsController {

    // TODO a SpringMVC tamogatja, hogy o feloldja a JSON-t, majd megnezem, hogy csinaltuk

    private static final String JSP_NAME = "viewContacts";
    private static final String SEARCH_CONTRACTS = "searchContacts";
    private static final String DELETE_CONTRACT = "deleteContact";
    private static final String ADD_CONTRACT = "addContact";

	@Autowired
	private UserProfileDataService userProfileDataService;

	@Autowired
	private UserConnectionService userConnectionService;

	@Autowired
	private UserCredentialService userCredentialService;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + ADD_CONTRACT, method = RequestMethod.POST)
	public ModelAndView addContact(Principal principal, @RequestBody String text) {
		final Long userId = userCredentialService.getUser(principal.getName()).getCredentialId();

		if ((text != null) && text.startsWith("id=")) {
            final String postContent = text.replace("id=contact", "");

			if (!postContent.isEmpty()) {
				userConnectionService.createUserConnection(userId, Long.parseLong(postContent));
			}
		}

		final ModelAndView result = new ModelAndView(JSP_NAME);
		result.addObject("contactList", userProfileDataService.getFriendsByUserId(extractIdFromPrincipal(principal)));
		return result;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + DELETE_CONTRACT, method = RequestMethod.POST)
	public ModelAndView deleteContact(Principal principal, @RequestBody String text) {
		final Long userId = userCredentialService.getUser(principal.getName()).getCredentialId();

		if ((text != null) && text.startsWith("id=")) {
			final String postContent = text.replace("id=contact", "");

			if (!postContent.isEmpty()) {
				userConnectionService.deleteUserConnection(userId, Long.parseLong(postContent));
			}
		}

        final ModelAndView result = new ModelAndView(JSP_NAME);
		result.addObject("contactList", userProfileDataService.getFriendsByUserId(extractIdFromPrincipal(principal)));
		return result;
	}

	private Long extractIdFromPrincipal(Principal principal) {
		return this.userCredentialService.getUser(principal.getName()).getCredentialId();
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + SEARCH_CONTRACTS, method = RequestMethod.GET)
	public ModelAndView getSearch() {
		return new ModelAndView(SEARCH_CONTRACTS);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet(Principal principal) {
		final ModelAndView result = new ModelAndView(JSP_NAME);
		result.addObject("contactList", userProfileDataService.getFriendsByUserId(extractIdFromPrincipal(principal)));
		return result;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + SEARCH_CONTRACTS, method = RequestMethod.POST)
	public ModelAndView search(Principal principal, @RequestBody String text) throws JSONException, UnsupportedEncodingException {
		final String encodedText = URLDecoder.decode(text, "UTF-8");
        final JSONObject jsonObject = new JSONObject(encodedText);
        final ModelAndView result = new ModelAndView(SEARCH_CONTRACTS);
		result.addObject("contactList", userProfileDataService.searchUserProfileDataDtos(
                extractIdFromPrincipal(principal),
                jsonObject.getString("email"),
                jsonObject.getString("fullName"),
                jsonObject.getString("place"),
                jsonObject.getString("job")
        ));

		return result;
	}
}
