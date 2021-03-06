package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.MessageDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.MessageEntity;
import hu.szoftverfolyamat.enums.MessageStatus;
import hu.szoftverfolyamat.exception.MessageServiceException;
import hu.szoftverfolyamat.repository.MessageRepository;
import hu.szoftverfolyamat.service.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserProfileDataService userProfileDataService;

    public MessageDto get(final long id) {
        final MessageEntity entity = messageRepository.findOne(id);
        return messageMapper.apply(entity);
    }

    public void create(final long senderId, final long recipientId, final String text) {
        final MessageEntity entity = new MessageEntity();
        entity.setUserFrom(userProfileDataService.findByUserCredentialId(senderId));
        entity.setUserTo(userProfileDataService.findByUserCredentialId(recipientId));
        entity.setCreatedAt(new Date());
        entity.setText(text);
        entity.setStatus(MessageStatus.ACTIVE);
        entity.setIsViewed(false);
        messageRepository.saveAndFlush(entity);
    }

    public void delete(final long userId, final long id) throws MessageServiceException {
        final MessageEntity entity = messageRepository.findOne(id);

        if (userId != entity.getUserFrom().getCredentialId()) {
            throw new MessageServiceException("Cannot delete message with other sender");
        }

        entity.setStatus(MessageStatus.DELETED);
        messageRepository.saveAndFlush(entity);
    }

    public List<MessageDto> getChat(final long senderId, final long recipientId) {
        return messageMapper.apply(messageRepository.findForUserPair(senderId, recipientId));
    }

    public Map<UserProfileDataDto, MessageDto> getAllChats(final long userId) {
        final List<MessageDto> messages = messageMapper.apply(messageRepository.findForUser(userId));
        final Map<UserProfileDataDto, MessageDto> result = new HashMap<>();

        for (final MessageDto message : messages) {
            final UserProfileDataDto otherParty = (message.getUserFrom().getCredentialId().equals(userId))
                    ? message.getUserTo()
                    : message.getUserFrom();

            if (!result.containsKey(otherParty)) {
                result.put(otherParty, message);
            }

        }

        return result;
    }

    public void markChatViewed(final long senderId, final long recipientId) {
        final List<MessageEntity> entities = messageRepository.findForUserPair(senderId, recipientId);

        for (final MessageEntity entity : entities) {
              if (!entity.getIsViewed() && entity.getUserTo().getCredentialId().equals(senderId)) {
                  entity.setIsViewed(true);
                  messageRepository.saveAndFlush(entity);
              }
        }
    }

    public int getNumberOfNonViewedMessages(final long recipientId) {
        return messageRepository.findUnreadForUser(recipientId).size();
    }
}
