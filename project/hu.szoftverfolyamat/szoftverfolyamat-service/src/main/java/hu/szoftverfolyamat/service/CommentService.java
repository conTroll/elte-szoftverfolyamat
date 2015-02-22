package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.CommentDto;
import hu.szoftverfolyamat.entity.CommentEntity;
import hu.szoftverfolyamat.repository.CommentRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserProfileDataService userProfileDataService;

	public void createComment(final Long authorCredentialId, final Long postId, final String text) {
		final CommentEntity commentEntity = new CommentEntity();
		commentEntity.setUserProfileData(userProfileDataService.findByUserCredentialId(authorCredentialId));
		commentEntity.setPostId(postId);
		commentEntity.setText(text);
		commentEntity.setCreationDate(new Date());
		commentRepository.saveAndFlush(commentEntity);
	}

	public void deleteCommentById(Long id) {
		commentRepository.delete(id);
	}

	public void deleteComments(final List<CommentEntity> entities) {
        commentRepository.delete(entities);
	}

	public List<CommentDto> parseEntitiesToDtos(final List<CommentEntity> entities) {
		final List<CommentDto> dtos = new ArrayList<CommentDto>();

		for (final CommentEntity entity : entities) {
			dtos.add(this.parseEntityToDto(entity));
		}

		return dtos;
	}

	private CommentDto parseEntityToDto(final CommentEntity entity) {
		final CommentDto commentDto = new CommentDto();
		commentDto.setUserProfileDataDto(userProfileDataService.parseEntityToDto(entity.getUserProfileData()));
		commentDto.setCommentId(entity.getCommentId());
		commentDto.setCreationDate(new SimpleDateFormat("YYYY.MM.dd HH:mm:ss").format(entity.getCreationDate()));
		commentDto.setPostId(entity.getPostId());
		commentDto.setText(entity.getText());
		return commentDto;
	}
}
