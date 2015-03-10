package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.MessageDto;
import hu.szoftverfolyamat.exception.MessageServiceException;
import hu.szoftverfolyamat.service.MessageService;
import hu.szoftverfolyamat.service.UserConnectionService;
import hu.szoftverfolyamat.web.helper.Role;
import hu.szoftverfolyamat.web.helper.Template;
import hu.szoftverfolyamat.web.helper.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(URI.MESSAGES)
@Secured({ Role.USER, Role.ADMIN })
public class MessageController extends BaseController {

    @Autowired
    private UserConnectionService userConnectionService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(URI.SHOW_BY_ID)
    public ModelAndView showChat(final Principal principal, @PathVariable("id") final Long recipientId) {
        final ModelAndView result = new ModelAndView(Template.MESSAGES_SHOW);
        result.addObject("messages", messageService.getChat(getCurrentUser(principal), recipientId));
        messageService.markChatViewed(getCurrentUser(principal), recipientId);
        return result;
    }

    @RequestMapping(URI.SHOW_ALL)
    public ModelAndView showAllChats(final Principal principal) {
        final ModelAndView result = new ModelAndView(Template.MESSAGES_SHOW_ALL);
        result.addObject("chats", messageService.getAllChats(getCurrentUser(principal)));
        return result;
    }

    // TODO akarjuk e, hogy csak baratnak kuldhessen
    @RequestMapping(value = URI.CREATE, method = RequestMethod.POST)
    public RedirectView doCreate(final Principal principal, @RequestParam("id") final Long recipientId, @RequestParam("text") final String text) {
        final List<Long> friends = userConnectionService.getFriendsIdByUserCredentialId(getCurrentUser(principal));
        String targetPage = URI.MESSAGES;

        if (friends.contains(recipientId)) {
            messageService.create(getCurrentUser(principal), recipientId, text);
            targetPage = getShowURI(recipientId);
        } else {
            // TODO flash message
        }

        return new RedirectView(targetPage, true);
    }

    // TODO hibak kezelese
    @RequestMapping(value = URI.DELETE, method = RequestMethod.POST)
    public RedirectView doDelete(final Principal principal, @RequestParam("id") final Long messageId) throws MessageServiceException {
        final MessageDto message = messageService.get(messageId);
        messageService.delete(getCurrentUser(principal), messageId);
        return new RedirectView(getShowURI(message.getUserTo().getCredentialId()), true);
    }

    private String getShowURI(final Long recipientId) {
        return (URI.MESSAGES + URI.SHOW_BY_ID).replace("{id}", recipientId.toString());
    }
}
