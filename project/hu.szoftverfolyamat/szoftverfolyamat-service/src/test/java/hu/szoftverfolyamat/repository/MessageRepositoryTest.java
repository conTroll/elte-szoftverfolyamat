package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.MessageEntity;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.enums.MessageStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service-application-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository testSubject;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private UserProfileDataRepository userProfileDataRepository;

    @Test
    public void testCreateAndFindScenarios() {
        // given
        final UserProfileData user = RepositoryTestHelper.createTestUser(userCredentialsRepository, userProfileDataRepository);
        final MessageEntity entity = createMessageEntity(user);

        // when
        testSubject.saveAndFlush(entity);

        // then
        Assert.assertTrue(testSubject.findAll().size() > 0);
        Assert.assertTrue(testSubject.findForUser(user.getCredentialId()).contains(entity));
        Assert.assertTrue(testSubject.findForUserPair(user.getCredentialId(), user.getCredentialId()).contains(entity));
        Assert.assertTrue(testSubject.findUnreadForUser(user.getCredentialId()).contains(entity));
    }

    private static MessageEntity createMessageEntity(final UserProfileData user) {
        final MessageEntity result = new MessageEntity();
        result.setCreatedAt(new Date());
        result.setIsViewed(false);
        result.setStatus(MessageStatus.ACTIVE);
        result.setText("test");
        result.setUserFrom(user);
        result.setUserTo(user);
        return result;
    }
}
