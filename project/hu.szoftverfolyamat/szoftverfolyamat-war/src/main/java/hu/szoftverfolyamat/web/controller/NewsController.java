package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.CommentService;
import hu.szoftverfolyamat.service.PostService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.requestobject.CommentRequest;
import hu.szoftverfolyamat.web.requestobject.IdRequest;
import hu.szoftverfolyamat.web.requestobject.TextRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
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
	public RedirectView createPost(final Principal principal,
			@Valid final @RequestBody TextRequest request,
			@NonNull final BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
    		return new RedirectView(URI.POSTS_SHOW, true);
        }
		
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
    public RedirectView createComment(final Principal principal,
    		@Valid final @RequestBody CommentRequest request,
    		@NonNull final BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		return new RedirectView(URI.COMMENTS_CREATE, true);
        }
    	
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
