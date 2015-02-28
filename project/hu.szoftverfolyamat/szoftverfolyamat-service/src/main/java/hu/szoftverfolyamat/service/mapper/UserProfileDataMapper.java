package hu.szoftverfolyamat.service.mapper;

import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.UserProfileData;
import hu.szoftverfolyamat.service.UserConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class UserProfileDataMapper extends AbstractMapper<UserProfileData, UserProfileDataDto> {

    @Autowired
    UserConnectionService userConnectionService;

    @Override
    public UserProfileDataDto apply(final UserProfileData entity) {
        final UserProfileDataDto result  = new UserProfileDataDto();
        result.setBirthday(new SimpleDateFormat("YYYY.MM.dd").format(entity.getBirthday()));
        result.setCredentialId(entity.getCredentialId());
        result.setEmail(entity.getEmail());
        result.setFullName(entity.getFullName());
        result.setHabitat(entity.getHabitat());
        result.setJob(entity.getJob());
        result.setPublicBirthday(entity.getPublicBirthday());
        result.setPublicHabitat(entity.getPublicHabitat());
        result.setPublicJobAndWorkplace(entity.getPublicJobAndWorkplace());
        result.setShortName(entity.getShortName());
        result.setWorkplace(entity.getWorkplace());
        result.setFriendNumber((long) userConnectionService.getFriendsIdByUserCredentialId(entity.getCredentialId()).size());
        return result;
    }
}
