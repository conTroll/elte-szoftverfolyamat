package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.ChannelProfileEntity;
import hu.szoftverfolyamat.entity.UserCredential;
import hu.szoftverfolyamat.entity.UserProfileData;

import java.util.Date;

public class RepositoryTestHelper {

    public static ChannelProfileEntity createTestChannel(final ChannelRepository channelRepository, final UserProfileData leader) {
        final ChannelProfileEntity result = new ChannelProfileEntity();
        result.setCreationDate(new Date());
        result.setDescription("description");
        result.setLeader(leader);
        result.setName("name");
        result.setOpen(true);
        channelRepository.saveAndFlush(result);
        return result;
    }
    
    public static UserProfileData createTestUser(final UserCredentialsRepository userCredentialsRepository, final UserProfileDataRepository userProfileDataRepository) {
    	return RepositoryTestHelper.createTestUser(userCredentialsRepository, userProfileDataRepository, "admin");
    }
    

    public static UserProfileData createTestUser(final UserCredentialsRepository userCredentialsRepository, final UserProfileDataRepository userProfileDataRepository, String username) {
        final UserCredential credential;
        credential = new UserCredential();
        credential.setEnabled(true);
        credential.setPassword("123");
        credential.setUsername(username);
        credential.setUserProfileData(null);
        credential.setUserRole(null);
        userCredentialsRepository.saveAndFlush(credential);


        final UserProfileData result = new UserProfileData();
        result.setBirthday(new Date());
        result.setCredentialId(credential.getCredentialId());
        result.setEmail("email@domain.tld");
        result.setFullName("full");
        result.setHabitat("habitat");
        result.setJob("job");
        result.setPublicBirthday(false);
        result.setPublicHabitat(false);
        result.setPublicJobAndWorkplace(false);
        result.setShortName("short");
        result.setWorkplace("workplace");
        userProfileDataRepository.saveAndFlush(result);

        return result;
    }
}
