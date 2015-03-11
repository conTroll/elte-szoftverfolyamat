package hu.szoftverfolyamat.dto;

import hu.szoftverfolyamat.enums.MessageStatus;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

public class MessageDtoTest {

    @Test
    public void testMutators() {
        // given
        final MessageDto testSubject = new MessageDto();
        final UserProfileDataDto userFrom = Mockito.mock(UserProfileDataDto.class);
        final UserProfileDataDto userTo = Mockito.mock(UserProfileDataDto.class);
        final Date date = new Date();

        // when
        testSubject.setCreatedAt(date);
        testSubject.setId(1);
        testSubject.setStatus(MessageStatus.ACTIVE);
        testSubject.setText("test");
        testSubject.setUserFrom(userFrom);
        testSubject.setUserTo(userTo);
        testSubject.setViewed(true);

        // then
        Assert.assertSame(date, testSubject.getCreatedAt());
        Assert.assertEquals(1, testSubject.getId());
        Assert.assertSame(MessageStatus.ACTIVE, testSubject.getStatus());
        Assert.assertEquals("test", testSubject.getText());
        Assert.assertSame(userFrom, testSubject.getUserFrom());
        Assert.assertSame(userTo, testSubject.getUserTo());
        Assert.assertTrue(testSubject.isViewed());
    }
}
