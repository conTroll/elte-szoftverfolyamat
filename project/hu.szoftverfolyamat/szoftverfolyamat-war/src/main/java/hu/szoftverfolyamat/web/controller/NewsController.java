package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.CommentService;
import hu.szoftverfolyamat.service.PostService;
import hu.szoftverfolyamat.service.UserCredentialService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;

import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Secured({ Role.USER, Role.ADMIN })
public class NewsController extends BaseController {

    @Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;


    @RequestMapping(URI.POSTS_SHOW)
    public ModelAndView show(final Principal principal) {
        final ModelAndView result = new ModelAndView(Template.POSTS_SHOW);
        result.addObject("currentUserId", getCurrentUser(principal));
        result.addObject("postList", postService.getPostsForUser(getCurrentUser(principal)));
        return result;
    }

    // TODO JSON parsing
	@RequestMapping(value = URI.POSTS_CREATE, method = RequestMethod.POST)
	public RedirectView createPost(final Principal principal, final @RequestBody String text) {
		if ((text != null) && text.startsWith("text=")) {
			final String postContent = text.replace("text=", "");

			if (!postContent.isEmpty()) {
				postService.createNewPost(postContent, getCurrentUser(principal));
			}
		}

        return new RedirectView(URI.POSTS_SHOW, true);
	}

    // TODO JSON parsing
	@RequestMapping(value = URI.POSTS_DELETE, method = RequestMethod.POST)
	public RedirectView deletePost(final @RequestBody String text) {
		if ((text != null) && text.startsWith("id=")) {
            String postContent = text.replace("id=post", "");
			if (!postContent.isEmpty()) {
				postService.deletePost(Long.parseLong(postContent));
			}
		}

        return new RedirectView(URI.POSTS_SHOW, true);
	}

    // TODO JSON parsing
    @RequestMapping(value = URI.COMMENTS_CREATE, method = RequestMethod.POST)
    public RedirectView deleteComment(final @RequestBody String text) throws UnsupportedEncodingException {
        if ((text != null) && text.startsWith("id=")) {
            final String postContent = text.replace("id=comment", "");
            if (!postContent.isEmpty()) {
                commentService.deleteCommentById(Long.parseLong(postContent));
            }
        }

        return new RedirectView(URI.POSTS_SHOW, true);
    }

    // TODO JSON parsing
    @RequestMapping(value = URI.COMMENTS_DELETE, method = RequestMethod.POST)
    public RedirectView createComment(final Principal principal, final @RequestBody String text) throws UnsupportedEncodingException {
        final String encodedText = URLDecoder.decode(text, "UTF-8");
        final JSONObject obj = new JSONObject(encodedText);
        final String postId = obj.getString("postId").replace("post", "");
        final String commentText = obj.getString("text");

        if ((postId != null) && (commentText != null) && !commentText.isEmpty()) {
            commentService.createComment(getCurrentUser(principal), Long.parseLong(postId), commentText);
        }

        return new RedirectView(URI.POSTS_SHOW, true);
    }
}
