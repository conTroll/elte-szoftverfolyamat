package hu.szoftverfolyamat.service.mapper;


import hu.szoftverfolyamat.dto.MessageDto;
import hu.szoftverfolyamat.entity.MessageEntity;
import hu.szoftverfolyamat.enums.MessageStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageMapperTest {

    private MessageMapper testSubject;

    @Before
    public void setUp() {
        testSubject = new MessageMapper();
        testSubject.userProfileDataMapper = Mockito.mock(UserProfileDataMapper.class);
    }

    @Test
    public void testApply() {
        // given
        final Date createdAt = new Date();
        final MessageEntity entity = new MessageEntity();
        entity.setCreatedAt(createdAt);
        entity.setIsViewed(false);
        entity.setStatus(MessageStatus.ACTIVE);
        entity.setText("test");

        // when
        final MessageDto result = testSubject.apply(entity);

        // then
        Assert.assertEquals(createdAt, result.getCreatedAt());
        Assert.assertEquals(false, result.isViewed());
        Assert.assertEquals(MessageStatus.ACTIVE, result.getStatus());
        Assert.assertEquals("test", result.getText());
    }

    @Test
    public void testApplyMultiple() {
        // given
        final List<MessageEntity> entities = new ArrayList<>();
        entities.add(new MessageEntity());
        entities.add(new MessageEntity());
        entities.add(new MessageEntity());
        entities.add(new MessageEntity());

        // when
        List<MessageDto> result = testSubject.apply(entities);

        // then
        Assert.assertEquals(4, result.size());
    }
}
