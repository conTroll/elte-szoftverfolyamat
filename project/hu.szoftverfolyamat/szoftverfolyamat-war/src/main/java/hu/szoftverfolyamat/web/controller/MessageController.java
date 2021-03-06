package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.MessageDto;
import hu.szoftverfolyamat.exception.MessageServiceException;
import hu.szoftverfolyamat.service.MessageService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.requestobject.IdRequest;
import hu.szoftverfolyamat.web.requestobject.MessageRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping(URI.MESSAGES)
@Secured({ Role.USER, Role.ADMIN })
public class MessageController extends BaseController {

//    @Autowired
//    private UserConnectionService userConnectionService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(URI.SHOW_BY_ID)
    public ModelAndView showChat(final Principal principal, @PathVariable("id") final Long otherPartyId) {
        final ModelAndView result = new ModelAndView(Template.MESSAGES_SHOW);
        result.addObject("currentUserId", getCurrentUser(principal));
        result.addObject("otherPartyId", otherPartyId);
        result.addObject("messages", messageService.getChat(getCurrentUser(principal), otherPartyId));
        messageService.markChatViewed(getCurrentUser(principal), otherPartyId);
        return result;
    }

    @RequestMapping(URI.SHOW_ALL)
    public ModelAndView showAllChats(final Principal principal) {
        final ModelAndView result = new ModelAndView(Template.MESSAGES_SHOW_ALL);
        result.addObject("currentUserId", getCurrentUser(principal));
        result.addObject("chats", messageService.getAllChats(getCurrentUser(principal)));
        return result;
    }

    @RequestMapping(URI.COUNTER)
    @ResponseBody
    public Integer showCounter(final Principal principal) {
        return messageService.getNumberOfNonViewedMessages(getCurrentUser(principal));
    }

    @RequestMapping(value = URI.CREATE, method = RequestMethod.POST)
    public RedirectView doCreate(final Principal principal, final @Valid @RequestBody MessageRequest request,
    		@NonNull final BindingResult bindingResult) {
       // final List<Long> friends = userConnectionService.getFriendsIdByUserCredentialId(getCurrentUser(principal));
       String targetPage = URI.MESSAGES + URI.SHOW_ALL;
        
        if (bindingResult.hasErrors()) {
			 
		    return new RedirectView(URI.MESSAGES + URI.CREATE, true);
        }
        
        //if (friends.contains(request.getRecipientId())) {
            messageService.create(getCurrentUser(principal), request.getRecipientId(), request.getText());
            targetPage = getShowURI(request.getRecipientId());
        //}

        return new RedirectView(targetPage, true);
    }

    @RequestMapping(value = URI.DELETE, method = RequestMethod.POST)
    public RedirectView doDelete(final Principal principal, final @RequestBody IdRequest request) throws MessageServiceException {
        final MessageDto message = messageService.get(request.getId());
        messageService.delete(getCurrentUser(principal), request.getId());
        return new RedirectView(getShowURI(message.getUserTo().getCredentialId()), true);
    }

    private String getShowURI(final Long recipientId) {
        return (URI.MESSAGES + URI.SHOW_BY_ID).replace("{id}", recipientId.toString());
    }
}
