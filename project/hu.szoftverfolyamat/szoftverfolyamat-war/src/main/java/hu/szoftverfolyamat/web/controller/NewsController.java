package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.CommentService;
import hu.szoftverfolyamat.service.PostService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.requestobject.CommentRequest;
import hu.szoftverfolyamat.web.requestobject.IdRequest;
import hu.szoftverfolyamat.web.requestobject.TextRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

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

	@RequestMapping(value = URI.POSTS_CREATE, method = RequestMethod.POST)
	public RedirectView createPost(final Principal principal, final @RequestBody TextRequest request) {
        postService.createNewPost(request.getText(), getCurrentUser(principal));
        return new RedirectView(URI.POSTS_SHOW, true);
	}

	@RequestMapping(value = URI.POSTS_DELETE, method = RequestMethod.POST)
	public RedirectView deletePost(final @RequestBody IdRequest request) {
        // TODO check if current user is the owner
        postService.deletePost(request.getId());
        return new RedirectView(URI.POSTS_SHOW, true);
	}

    @RequestMapping(value = URI.COMMENTS_CREATE, method = RequestMethod.POST)
    public RedirectView createComment(final Principal principal, final @RequestBody CommentRequest request) {
        if (!request.getText().isEmpty()) {
            commentService.createComment(getCurrentUser(principal), request.getPostId(), request.getText());
        }

        return new RedirectView(URI.POSTS_SHOW, true);
    }

    @RequestMapping(value = URI.COMMENTS_DELETE, method = RequestMethod.POST)
    public RedirectView deleteComment(final @RequestBody IdRequest request) {
        // TODO check if current user is the owner
        commentService.deleteCommentById(request.getId());
        return new RedirectView(URI.POSTS_SHOW, true);
    }
}
