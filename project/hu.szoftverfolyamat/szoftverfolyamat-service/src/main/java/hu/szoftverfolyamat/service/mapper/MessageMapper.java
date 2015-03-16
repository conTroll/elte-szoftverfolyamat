package hu.szoftverfolyamat.service.mapper;


import hu.szoftverfolyamat.dto.MessageDto;
import hu.szoftverfolyamat.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper extends AbstractMapper<MessageEntity, MessageDto> {

    @Autowired
    UserProfileDataMapper userProfileDataMapper;

    @Override
    public MessageDto apply(final MessageEntity entity) {
        final MessageDto result = new MessageDto();
        result.setCreatedAt(entity.getCreatedAt());
        result.setId(entity.getId());
        result.setStatus(entity.getStatus());
        result.setText(entity.getText());

        if (entity.getUserFrom() != null) {
            result.setUserFrom(userProfileDataMapper.apply(entity.getUserFrom()));
        }

        if (entity.getUserTo() != null) {
            result.setUserTo(userProfileDataMapper.apply(entity.getUserTo()));
        }

        result.setViewed(entity.getIsViewed());
        return result;
    }
}
