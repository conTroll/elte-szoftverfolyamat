package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.dto.MessageDto;
import hu.szoftverfolyamat.exception.MessageServiceException;
import hu.szoftverfolyamat.service.MessageService;
import hu.szoftverfolyamat.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/messages")
public class MessageController extends BaseController {

    private static final String TEMPLATE_SHOW = "messagesShow";
    private static final String TEMPLATE_SHOW_ALL = "messagesShowAll";

    @Autowired
    private UserCredentialService userCredentialService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/show/{recipientId}")
    public ModelAndView showChat(final Principal principal, @PathVariable("recipientId") final Long recipientId) {
        final ModelAndView result = new ModelAndView(TEMPLATE_SHOW);
        result.addObject("messages", messageService.getChat(getCurrentUser(principal), recipientId));
        return result;
    }

    @RequestMapping("/show_all")
    public ModelAndView showAllChats(final Principal principal) {
        final ModelAndView result = new ModelAndView(TEMPLATE_SHOW_ALL);
        result.addObject("chats", messageService.getAllChats(getCurrentUser(principal)));
        return result;
    }

    // TODO akarjuk e, hogy csak baratnak kuldhessen
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createMessage(final Principal principal, @RequestParam("recipientId") final Long recipientId, @RequestParam("text") final String text) {
        messageService.create(getCurrentUser(principal), recipientId, text);
        return new RedirectView("/messages/show/" + recipientId, true);
    }

    // TODO hibak kezelese
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RedirectView deleteMessage(final Principal principal, @RequestParam("messageId") final Long messageId) throws MessageServiceException {
        final MessageDto message = messageService.get(messageId);
        messageService.delete(getCurrentUser(principal), messageId);
        return new RedirectView("/messages/show/" + message.getUserTo().getCredentialId(), true);
    }

    private Long getCurrentUser(final Principal principal) {
        return userCredentialService.getUser(principal.getName()).getCredentialId();
    }
}
