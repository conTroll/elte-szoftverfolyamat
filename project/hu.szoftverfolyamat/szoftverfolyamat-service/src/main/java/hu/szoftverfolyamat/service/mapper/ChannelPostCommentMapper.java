package hu.szoftverfolyamat.service.mapper;

import hu.szoftverfolyamat.dto.ChannelPostCommentDto;
import hu.szoftverfolyamat.dto.ChannelPostDto;
import hu.szoftverfolyamat.dto.UserProfileDataDto;
import hu.szoftverfolyamat.entity.ChannelPostCommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelPostCommentMapper extends AbstractMapper<ChannelPostCommentEntity, ChannelPostCommentDto> {
	
	@Autowired
	private UserProfileDataMapper userMapper;
	
	@Autowired
	private ChannelPostMapper channelPostMapper;

	@Override
	public ChannelPostCommentDto apply(ChannelPostCommentEntity entity) {
		
		ChannelPostCommentDto dto = new ChannelPostCommentDto();
		UserProfileDataDto authorDto = this.userMapper.apply(entity.getAuthor());
		dto.setAuthor(authorDto);
		dto.setCreationDate(entity.getCreationDate());
		dto.setId(entity.getId());
		ChannelPostDto postDto = this.channelPostMapper.apply(entity.getPost());
		dto.setPost(postDto);
		dto.setText(entity.getText());
		return dto;
		
	}

}
