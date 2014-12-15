package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.CommentService;
import hu.szoftverfolyamat.service.PostService;
import hu.szoftverfolyamat.service.UserCredentialService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {

	public static final String JSP_NAME = "news";
	public static final String CREATE_POST = "createPost";
	public static final String CREATE_COMMENT = "createComment";
	public static final String DELETE_POST = "deletePost";
	public static final String DELETE_COMMENT = "deleteComment";

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserCredentialService userCredentialService;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + NewsController.CREATE_COMMENT, method = RequestMethod.POST)
	public ModelAndView createComment(Principal principal,
			@RequestBody String text) throws UnsupportedEncodingException {
		ModelAndView modelAndView;
		String postId;
		String commentText;
		JSONObject obj;

		String encoded = URLDecoder.decode(text, "UTF-8");

		obj = new JSONObject(encoded);
		postId = obj.getString("postId");
		postId = postId.replace("post", "");
		commentText = obj.getString("text");

		if ((postId != null) && (commentText != null) && !commentText.isEmpty()) {
			this.commentService.createComment(
					this.extractIdFromPrincipal(principal),
					Long.parseLong(postId), commentText);
		}

		modelAndView = new ModelAndView(NewsController.JSP_NAME);
		modelAndView.addObject("currentUserId", this.userCredentialService
				.getUser(principal.getName()).getCredentialId());
		modelAndView.addObject("postList", this.postService
				.getPostsForUser(this.userCredentialService.getUser(
						principal.getName()).getCredentialId()));
		return modelAndView;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + NewsController.CREATE_POST, method = RequestMethod.POST)
	public ModelAndView createPost(Principal principal, @RequestBody String text) {
		ModelAndView modelAndView;
		String postContent;

		if ((text != null) && text.startsWith("text=")) {
			postContent = text.replace("text=", "");
			if (!postContent.isEmpty()) {
				this.postService.createNewPost(postContent,
						this.userCredentialService.getUser(principal.getName())
								.getCredentialId());
			}
		}

		modelAndView = new ModelAndView(NewsController.JSP_NAME);
		modelAndView.addObject("currentUserId", this.userCredentialService
				.getUser(principal.getName()).getCredentialId());
		modelAndView.addObject("postList", this.postService
				.getPostsForUser(this.userCredentialService.getUser(
						principal.getName()).getCredentialId()));
		return modelAndView;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + NewsController.DELETE_COMMENT, method = RequestMethod.POST)
	public ModelAndView deleteComment(Principal principal,
			@RequestBody String text) throws UnsupportedEncodingException {
		ModelAndView modelAndView;
		String postContent;

		if ((text != null) && text.startsWith("id=")) {
			postContent = text.replace("id=comment", "");
			if (!postContent.isEmpty()) {
				this.commentService.deleteCommentById(Long
						.parseLong(postContent));
			}
		}

		modelAndView = new ModelAndView(NewsController.JSP_NAME);
		modelAndView.addObject("currentUserId", this.userCredentialService
				.getUser(principal.getName()).getCredentialId());
		modelAndView.addObject("postList", this.postService
				.getPostsForUser(this.userCredentialService.getUser(
						principal.getName()).getCredentialId()));
		return modelAndView;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + NewsController.DELETE_POST, method = RequestMethod.POST)
	public ModelAndView deletePost(Principal principal, @RequestBody String text) {
		ModelAndView modelAndView;
		String postContent;

		if ((text != null) && text.startsWith("id=")) {
			postContent = text.replace("id=post", "");
			if (!postContent.isEmpty()) {
				this.postService.deletePost(Long.parseLong(postContent));
			}
		}

		modelAndView = new ModelAndView(NewsController.JSP_NAME);
		modelAndView.addObject("currentUserId", this.userCredentialService
				.getUser(principal.getName()).getCredentialId());
		modelAndView.addObject("postList", this.postService
				.getPostsForUser(this.userCredentialService.getUser(
						principal.getName()).getCredentialId()));
		return modelAndView;
	}

	private Long extractIdFromPrincipal(Principal principal) {
		return this.userCredentialService.getUser(principal.getName())
				.getCredentialId();
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + NewsController.JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet(Principal principal) {
		ModelAndView modelAndView;

		modelAndView = new ModelAndView(NewsController.JSP_NAME);
		modelAndView.addObject("currentUserId", this.userCredentialService
				.getUser(principal.getName()).getCredentialId());
		modelAndView.addObject("postList", this.postService
				.getPostsForUser(this.userCredentialService.getUser(
						principal.getName()).getCredentialId()));
		return modelAndView;
	}

}
