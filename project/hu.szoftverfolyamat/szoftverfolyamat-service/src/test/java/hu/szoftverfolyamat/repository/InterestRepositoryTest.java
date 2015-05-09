package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.ChannelProfileEntity;
import hu.szoftverfolyamat.entity.InterestEntity;
import hu.szoftverfolyamat.entity.UserProfileData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service-application-context.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class InterestRepositoryTest {

    @Autowired
    private InterestRepository testSubject;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private UserProfileDataRepository userProfileDataRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    public void testListAll() {
        // when
        final List<InterestEntity> result1 = testSubject.findAll();

        // then
        Assert.assertEquals(0, result1.size());

        // given
        final InterestEntity interest1 = createInterest("a");
        final InterestEntity interest2 = createInterest("b");

        // when
        final List<InterestEntity> result2 = testSubject.findAll();

        // then
        Assert.assertEquals(2, result2.size());
        Assert.assertTrue(result2.contains(interest1));
        Assert.assertTrue(result2.contains(interest2));
    }

    @Test
    public void testUserBased() {
        // given
        final UserProfileData user = RepositoryTestHelper.createTestUser(userCredentialsRepository, userProfileDataRepository);
        final InterestEntity interest = createInterest("a");

        // when
        final List<InterestEntity> result1 = user.getInterests();

        // then
        Assert.assertNull(result1);

        // when
        final List<InterestEntity> userInterests = new ArrayList<>();
        userInterests.add(interest);
        user.setInterests(userInterests);
        userProfileDataRepository.saveAndFlush(user);


        final UserProfileData loadedUser = userProfileDataRepository.findOne(user.getCredentialId());
        final List<InterestEntity> result2 = loadedUser.getInterests();

        // then
        Assert.assertEquals(1, result2.size());
        Assert.assertTrue(result2.contains(interest));

        // when
        loadedUser.getInterests().remove(interest);
        userProfileDataRepository.saveAndFlush(loadedUser);
        final UserProfileData loadedUser2 = userProfileDataRepository.findOne(loadedUser.getCredentialId());
        final List<InterestEntity> result3 = loadedUser2.getInterests();

        // then
        Assert.assertEquals(0, result3.size());
    }

    @Test
    public void testChannelBased() {
        // given
        final UserProfileData leader = RepositoryTestHelper.createTestUser(userCredentialsRepository, userProfileDataRepository);
        final ChannelProfileEntity channel = RepositoryTestHelper.createTestChannel(channelRepository, leader);
        final InterestEntity interest = createInterest("b");

        // when
        final List<InterestEntity> result1 = channel.getInterests();

        // then
        Assert.assertNull(result1);

        // when
        final List<InterestEntity> channelInterests = new ArrayList<>();
        channelInterests.add(interest);
        channel.setInterests(channelInterests);
        channelRepository.saveAndFlush(channel);


        final ChannelProfileEntity loadedChannel = channelRepository.findOne(channel.getId());
        final List<InterestEntity> result2 = loadedChannel.getInterests();

        // then
        Assert.assertEquals(1, result2.size());
        Assert.assertTrue(result2.contains(interest));

        // when
        loadedChannel.getInterests().remove(interest);
        channelRepository.saveAndFlush(loadedChannel);
        final ChannelProfileEntity loadedChannel2 = channelRepository.findOne(loadedChannel.getId());
        final List<InterestEntity> result3 = loadedChannel2.getInterests();

        // then
        Assert.assertEquals(0, result3.size());
    }

    private InterestEntity createInterest(String name) {
        InterestEntity result = new InterestEntity();
        result.setName(name);
        testSubject.saveAndFlush(result);
        return result;
    }

}
