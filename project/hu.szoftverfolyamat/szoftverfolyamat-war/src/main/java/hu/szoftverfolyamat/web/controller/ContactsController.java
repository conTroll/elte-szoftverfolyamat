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

	public static final String JSP_NAME = "viewContacts";
	public static final String SEARCH_CONTRACTS = "searchContacts";
	public static final String DELETE_CONTRACT = "deleteContact";
	public static final String ADD_CONTRACT = "addContact";

	@Autowired
	private UserProfileDataService userProfileDataService;

	@Autowired
	private UserConnectionService userConnectionService;

	@Autowired
	private UserCredentialService userCredentialService;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + ContactsController.ADD_CONTRACT, method = RequestMethod.POST)
	public ModelAndView addContact(Principal principal, @RequestBody String text) {
		ModelAndView modelAndView;
		Long userId;
		String postContent;

		userId = this.userCredentialService.getUser(principal.getName())
				.getCredentialId();

		if ((text != null) && text.startsWith("id=")) {
			postContent = text.replace("id=contact", "");
			if (!postContent.isEmpty()) {
				this.userConnectionService.createUserConnection(userId,
						Long.parseLong(postContent));
			}
		}

		modelAndView = new ModelAndView(ContactsController.JSP_NAME);
		modelAndView.addObject("contactList", this.userProfileDataService
				.getFriendsByUserId(this.extractIdFromPrincipal(principal)));
		return modelAndView;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + ContactsController.DELETE_CONTRACT, method = RequestMethod.POST)
	public ModelAndView deleteContact(Principal principal,
			@RequestBody String text) {
		ModelAndView modelAndView;
		Long userId;
		String postContent;

		userId = this.userCredentialService.getUser(principal.getName())
				.getCredentialId();

		if ((text != null) && text.startsWith("id=")) {
			postContent = text.replace("id=contact", "");
			if (!postContent.isEmpty()) {
				this.userConnectionService.deleteUserConnection(userId,
						Long.parseLong(postContent));
			}
		}

		modelAndView = new ModelAndView(ContactsController.JSP_NAME);
		modelAndView.addObject("contactList", this.userProfileDataService
				.getFriendsByUserId(this.extractIdFromPrincipal(principal)));
		return modelAndView;
	}

	private Long extractIdFromPrincipal(Principal principal) {
		return this.userCredentialService.getUser(principal.getName())
				.getCredentialId();
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + ContactsController.SEARCH_CONTRACTS, method = RequestMethod.GET)
	public ModelAndView getSearch() {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(ContactsController.SEARCH_CONTRACTS);
		return modelAndView;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + ContactsController.JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet(Principal principal) {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(ContactsController.JSP_NAME);
		modelAndView.addObject("contactList", this.userProfileDataService
				.getFriendsByUserId(this.extractIdFromPrincipal(principal)));
		return modelAndView;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + ContactsController.SEARCH_CONTRACTS, method = RequestMethod.POST)
	public ModelAndView search(Principal principal, @RequestBody String text)
			throws JSONException, UnsupportedEncodingException {
		ModelAndView modelAndView;
		JSONObject obj;
		String email;
		String fullName;
		String place;
		String job;

		String encoded = URLDecoder.decode(text, "UTF-8");

		obj = new JSONObject(encoded);
		email = obj.getString("email");
		fullName = obj.getString("fullName");
		place = obj.getString("place");
		job = obj.getString("job");

		modelAndView = new ModelAndView(ContactsController.SEARCH_CONTRACTS);
		modelAndView.addObject("contactList", this.userProfileDataService
				.searchUserProfileDataDtos(
						this.extractIdFromPrincipal(principal), email,
						fullName, place, job));
		return modelAndView;
	}
}
