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

    private static final String JSP_NAME = "news";
    private static final String CREATE_POST = "createPost";
    private static final String CREATE_COMMENT = "createComment";
    private static final String DELETE_POST = "deletePost";
    private static final String DELETE_COMMENT = "deleteComment";

    @Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserCredentialService userCredentialService;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + CREATE_COMMENT, method = RequestMethod.POST)
	public ModelAndView createComment(Principal principal, @RequestBody String text) throws UnsupportedEncodingException {
		final String encodedText = URLDecoder.decode(text, "UTF-8");
        final JSONObject obj = new JSONObject(encodedText);
		final String postId = obj.getString("postId").replace("post", "");
		final String commentText = obj.getString("text");

		if ((postId != null) && (commentText != null) && !commentText.isEmpty()) {
			commentService.createComment(extractIdFromPrincipal(principal), Long.parseLong(postId), commentText);
		}

        // TODO RedirectView instead
        return generateResult(principal);
	}

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + CREATE_POST, method = RequestMethod.POST)
	public ModelAndView createPost(Principal principal, @RequestBody String text) {
		if ((text != null) && text.startsWith("text=")) {
			final String postContent = text.replace("text=", "");
			if (!postContent.isEmpty()) {
				postService.createNewPost(postContent, userCredentialService.getUser(principal.getName()).getCredentialId());
			}
		}

        // TODO RedirectView instead
        return generateResult(principal);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + DELETE_COMMENT, method = RequestMethod.POST)
	public ModelAndView deleteComment(Principal principal, @RequestBody String text) throws UnsupportedEncodingException {
		if ((text != null) && text.startsWith("id=")) {
            final String postContent = text.replace("id=comment", "");
			if (!postContent.isEmpty()) {
				commentService.deleteCommentById(Long.parseLong(postContent));
			}
		}

        // TODO RedirectView instead
        return generateResult(principal);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + DELETE_POST, method = RequestMethod.POST)
	public ModelAndView deletePost(Principal principal, @RequestBody String text) {
		if ((text != null) && text.startsWith("id=")) {
            String postContent = text.replace("id=post", "");
			if (!postContent.isEmpty()) {
				postService.deletePost(Long.parseLong(postContent));
			}
		}

        // TODO RedirectView instead
        return generateResult(principal);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/" + JSP_NAME, method = RequestMethod.GET)
	public ModelAndView handleGet(final Principal principal) {
		return generateResult(principal);
	}

    private ModelAndView generateResult(final Principal principal) {
        final ModelAndView result = new ModelAndView(JSP_NAME);
        result.addObject("currentUserId", userCredentialService.getUser(principal.getName()).getCredentialId());
        result.addObject("postList", postService.getPostsForUser(userCredentialService.getUser(principal.getName()).getCredentialId()));
        return result;
    }

    private Long extractIdFromPrincipal(final Principal principal) {
        return userCredentialService.getUser(principal.getName()).getCredentialId();
    }
}
