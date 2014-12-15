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

	public void createComment(Long authorCredentialId, Long postId, String text) {
		CommentEntity commentEntity;

		commentEntity = new CommentEntity();
		commentEntity.setUserProfileData(this.userProfileDataService
				.findByUserCredentialId(authorCredentialId));
		commentEntity.setPostId(postId);
		commentEntity.setText(text);
		commentEntity.setCreationDate(new Date());
		this.commentRepository.saveAndFlush(commentEntity);
	}

	public void deleteCommentById(Long id) {
		this.commentRepository.delete(id);
	}

	public void deleteComments(List<CommentEntity> entities) {
		this.commentRepository.delete(entities);
	}

	public List<CommentDto> parseEntitiesToDtos(List<CommentEntity> entities) {
		List<CommentDto> dtos;

		dtos = new ArrayList<CommentDto>();
		for (CommentEntity entity : entities) {
			dtos.add(this.parseEntityToDto(entity));
		}
		return dtos;
	}

	public CommentDto parseEntityToDto(CommentEntity entity) {
		CommentDto commentDto;

		commentDto = new CommentDto();
		commentDto.setUserProfileDataDto(this.userProfileDataService
				.parseEntityToDto(entity.getUserProfileData()));
		commentDto.setCommentId(entity.getCommentId());
		commentDto.setCreationDate(new SimpleDateFormat("YYYY.MM.dd HH:mm:ss")
				.format(entity.getCreationDate()));
		commentDto.setPostId(entity.getPostId());
		commentDto.setText(entity.getText());
		return commentDto;
	}

}
