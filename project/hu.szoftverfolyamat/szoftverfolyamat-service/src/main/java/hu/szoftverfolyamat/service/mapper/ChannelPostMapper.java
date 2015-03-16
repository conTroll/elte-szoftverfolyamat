package hu.szoftverfolyamat.service.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.szoftverfolyamat.dto.ChannelPostCommentDto;
import hu.szoftverfolyamat.dto.ChannelPostDto;
import hu.szoftverfolyamat.dto.ChannelProfileDto;
import hu.szoftverfolyamat.entity.ChannelPostEntity;

@Service
public class ChannelPostMapper extends AbstractMapper<ChannelPostEntity, ChannelPostDto> {

	@Autowired
	private ChannelProfileMapper channelProfileMapper;
	
	@Autowired
	private ChannelPostCommentMapper channelCommentMapper;
	
	@Override
	public ChannelPostDto apply(ChannelPostEntity entity) {
		
		ChannelPostDto dto = new ChannelPostDto();
		ChannelProfileDto channelDto = this.channelProfileMapper.apply(entity.getChannel());
		dto.setChannel(channelDto);
		List<ChannelPostCommentDto> comments = this.channelCommentMapper.apply(entity.getComments());
		dto.setComments(comments);
		dto.setCreationDate(entity.getCreationDate());
		dto.setId(entity.getId());
		dto.setText(entity.getText());
		return dto;
		
	}

}
