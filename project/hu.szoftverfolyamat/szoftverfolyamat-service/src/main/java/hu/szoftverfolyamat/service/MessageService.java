package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.MessageDto;
import hu.szoftverfolyamat.entity.MessageEntity;
import hu.szoftverfolyamat.enums.MessageStatus;
import hu.szoftverfolyamat.exception.MessageServiceException;
import hu.szoftverfolyamat.service.mapper.MessageMapper;
import hu.szoftverfolyamat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserProfileDataService userProfileDataService;

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

    public List<MessageDto> getChat(final Long senderId, final Long recipientId) {
        return messageMapper.apply(messageRepository.findForUserPair(senderId, recipientId));
    }

    // TODO
    public List<MessageDto> getAllChats(final Long senderId) {
        return new ArrayList<MessageDto>();
    }
}
