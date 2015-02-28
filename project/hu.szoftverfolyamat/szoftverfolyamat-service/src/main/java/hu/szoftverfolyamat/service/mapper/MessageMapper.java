package hu.szoftverfolyamat.service.mapper;


import hu.szoftverfolyamat.dto.MessageDto;
import hu.szoftverfolyamat.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper extends AbstractMapper<MessageEntity, MessageDto> {

    @Autowired
    private UserProfileDataMapper userProfileDataMapper;

    @Override
    public MessageDto apply(final MessageEntity entity) {
        final MessageDto result = new MessageDto();
        result.setCreatedAt(entity.getCreatedAt());
        result.setId(entity.getId());
        result.setStatus(entity.getStatus());
        result.setText(entity.getText());
        result.setUserFrom(userProfileDataMapper.apply(entity.getUserFrom()));
        result.setUserTo(userProfileDataMapper.apply(entity.getUserTo()));
        return result;
    }
}
