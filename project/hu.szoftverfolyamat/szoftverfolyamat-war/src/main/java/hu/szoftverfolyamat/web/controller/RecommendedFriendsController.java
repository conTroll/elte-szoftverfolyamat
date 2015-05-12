package hu.szoftverfolyamat.web.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import lombok.NonNull;
import hu.szoftverfolyamat.enums.RecommendBase;
import hu.szoftverfolyamat.exception.UserServiceException;
import hu.szoftverfolyamat.service.RecommendService;
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
public class RecommendedFriendsController extends BaseController {

	@Autowired
	private RecommendService recommendService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm() {
		RecommendedFriendsRequest recommendedFriendsRequest = new RecommendedFriendsRequest();

		final ModelAndView result = new ModelAndView(
				Template.RECOMMENDED_FRIENDS);
		result.addObject("profileFormRequest", recommendedFriendsRequest);
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView showRecommendedFriends(
			Principal principal,
			@ModelAttribute(value = "recommendedFriendsRequest") RecommendedFriendsRequest recommendedFriendsRequest) {
		ModelAndView modelAndView;
		long currentUserId = this.getCurrentUser(principal);
		Set<RecommendBase> recommendBases;

		recommendBases = new HashSet<>();
		modelAndView = new ModelAndView(Template.RECOMMENDED_FRIENDS_PAGE);

		if (recommendedFriendsRequest.getRecommendByWorkplace() != null
				&& !recommendedFriendsRequest.getRecommendByWorkplace().equals(
						"off")) {
			recommendBases.add(RecommendBase.COMMON_WORKPLACE);
			modelAndView.addObject("isRecommendByWorkplace", true);
		}
		if (recommendedFriendsRequest.getRecommendByHabitat() != null
				&& !recommendedFriendsRequest.getRecommendByHabitat().equals(
						"off")) {
			recommendBases.add(RecommendBase.COMMON_HABITAT);
			modelAndView.addObject("isRecommendByHabitat", true);
		}
		if (recommendedFriendsRequest.getRecommendByJob() != null
				&& !recommendedFriendsRequest.getRecommendByJob().equals(
						"off")) {
			recommendBases.add(RecommendBase.COMMON_JOB);
			modelAndView.addObject("isRecommendByJob", true);
		}
		if (recommendedFriendsRequest.getRecommendByInterests() != null
				&& !recommendedFriendsRequest.getRecommendByInterests().equals(
						"off")) {
			recommendBases.add(RecommendBase.COMMON_INTERESTS);
			modelAndView.addObject("isRecommendByInterests", true);
		}

		modelAndView.addObject("recommendFriendsMap", recommendService.recommendFriends(currentUserId, recommendBases));

		return modelAndView;
	}
}
