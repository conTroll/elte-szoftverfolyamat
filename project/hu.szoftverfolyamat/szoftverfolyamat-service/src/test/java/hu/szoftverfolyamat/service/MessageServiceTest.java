package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.MessageEntity;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.repository.MessageRepository;
import hu.szoftverfolyamat.service.mapper.MessageMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageServiceTest {

    private MessageService testSubject;

    private final MessageMapper messageMapper = new MessageMapper();

    private final MessageRepository messageRepository = Mockito.mock(MessageRepository.class);

    private final UserProfileDataService userProfileDataService = Mockito.mock(UserProfileDataService.class);

    @Before
    public void setUp() {
        testSubject = new MessageService();
        testSubject.messageMapper = messageMapper;
        testSubject.messageRepository = messageRepository;
        testSubject.userProfileDataService = userProfileDataService;
    }

    @Test
    public void testGet() {
        // given
        final Long id = 1l;
        Mockito.when(messageRepository.findOne(id)).thenReturn(new MessageEntity());

        // when
        testSubject.get(id);

        // then
        Mockito.verify(messageRepository, Mockito.times(1)).findOne(id);
    }

    @Test
    public void testCreate() {
        // when
        testSubject.create(1, 2, "text");

        // then
        final InOrder inOrder = Mockito.inOrder(messageRepository, userProfileDataService);
        inOrder.verify(userProfileDataService, Mockito.times(1)).findByUserCredentialId(1);
        inOrder.verify(userProfileDataService, Mockito.times(1)).findByUserCredentialId(2);
        inOrder.verify(messageRepository, Mockito.times(1)).saveAndFlush(Mockito.any(MessageEntity.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testGetChat() {
        // given
        Mockito.when(messageRepository.findForUserPair(1, 2)).thenReturn(new ArrayList<MessageEntity>());

        // when
        testSubject.getChat(1, 2);

        // then
        Mockito.verify(messageRepository, Mockito.times(1)).findForUserPair(1, 2);
    }

    @Test
    public void testGetAllChats() {
        // given
        Mockito.when(messageRepository.findForUser(1)).thenReturn(new ArrayList<MessageEntity>());

        // when
        testSubject.getAllChats(1);

        // then
        Mockito.verify(messageRepository, Mockito.times(1)).findForUser(1);
    }

    @Test
    public void testMarkChatViewed() {
        // given
        final UserProfileData user = new UserProfileData();
        user.setCredentialId(1l);

        final MessageEntity message1 = new MessageEntity();
        final MessageEntity message2 = new MessageEntity();
        message1.setIsViewed(true);
        message2.setIsViewed(false);
        message2.setUserTo(user);

        final List<MessageEntity> messages = Arrays.asList(message1, message2);
        Mockito.when(messageRepository.findForUserPair(1, 2)).thenReturn(messages);

        // when
        testSubject.markChatViewed(1, 2);

        // then
        final InOrder inOrder = Mockito.inOrder(messageRepository);
        inOrder.verify(messageRepository, Mockito.times(1)).findForUserPair(1, 2);
        inOrder.verify(messageRepository, Mockito.times(1)).saveAndFlush(message2);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testGetNumberOfNonViewedMessages() {
        // when
        testSubject.getNumberOfNonViewedMessages(1);

        // then
        Mockito.verify(messageRepository, Mockito.times(1)).findUnreadForUser(1);
    }
}
