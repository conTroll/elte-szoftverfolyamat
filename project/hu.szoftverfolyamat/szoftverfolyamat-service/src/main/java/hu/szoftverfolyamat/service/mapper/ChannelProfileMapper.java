package hu.szoftverfolyamat.service.mapper;

import hu.szoftverfolyamat.dto.ChannelProfileDto;
import hu.szoftverfolyamat.entity.ChannelProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelProfileMapper extends AbstractMapper<ChannelProfileEntity, ChannelProfileDto> {

	@Autowired
	private UserProfileDataMapper userMapper;
	
	@Override
	public ChannelProfileDto apply(ChannelProfileEntity entity) {
		
		ChannelProfileDto dto = new ChannelProfileDto();
		dto.setCreationDate(entity.getCreationDate());
		dto.setDescription(entity.getDescription());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setOpen(entity.isOpen());
		dto.setLeader(this.userMapper.apply(entity.getLeader()));
		return dto;
		
	}
	
	

}
