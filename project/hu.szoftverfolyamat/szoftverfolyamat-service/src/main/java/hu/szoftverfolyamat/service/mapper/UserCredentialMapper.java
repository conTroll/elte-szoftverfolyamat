package hu.szoftverfolyamat.service.mapper;

import hu.szoftverfolyamat.dto.UserCredentialDto;
import hu.szoftverfolyamat.entity.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialMapper extends AbstractMapper<UserCredential, UserCredentialDto> {

	@Autowired
    private UserProfileDataMapper userProfileDataMapper;
	
	@Override
	public UserCredentialDto apply(UserCredential entity) {
		UserCredentialDto dto;
		
		dto = new UserCredentialDto();
		dto.setCredentialId(entity.getCredentialId());
		dto.setEnabled(entity.isEnabled());
		dto.setPassword(entity.getPassword());
		dto.setUsername(entity.getUsername());
		dto.setUserProfileDataDto(userProfileDataMapper.apply(entity.getUserProfileData()));
		return dto;
	}

}
