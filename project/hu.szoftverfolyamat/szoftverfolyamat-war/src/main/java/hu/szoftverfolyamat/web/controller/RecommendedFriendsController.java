package hu.szoftverfolyamat.web.controller;

import java.text.ParseException;

import javax.validation.Valid;

import lombok.NonNull;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.requestobject.ProfileFormRequest;
import hu.szoftverfolyamat.web.requestobject.RecommendedFriendsRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Secured({ Role.USER, Role.ADMIN })
@RequestMapping(value = URI.RECOMMENDED_FRIENDS)
public class RecommendedFriendsController {
	
//	@Autowired

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		RecommendedFriendsRequest recommendedFriendsRequest = new RecommendedFriendsRequest();

        final  ModelAndView result = new ModelAndView(Template.RECOMMENDED_FRIENDS);
		result.addObject("profileFormRequest", recommendedFriendsRequest);
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView doRegistration(@ModelAttribute(value = "recommendedFriendsRequest") RecommendedFriendsRequest recommendedFriendsRequest) {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(URI.RECOMMENDED_FRIENDS);

		return modelAndView;
	}
}
