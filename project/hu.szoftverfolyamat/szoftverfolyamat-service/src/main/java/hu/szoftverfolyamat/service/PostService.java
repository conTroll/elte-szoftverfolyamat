package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.PostDto;
import hu.szoftverfolyamat.entity.PostEntity;
import hu.szoftverfolyamat.repository.PostRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserProfileDataService userProfileDataService;

	@Autowired
	private UserConnectionService userConnectionService;

	public void createNewPost(String text, long ownerId) {
		PostEntity postEntity;

		postEntity = new PostEntity();
		postEntity.setUserProfileData(this.userProfileDataService
				.findByUserCredentialId(ownerId));
		postEntity.setText(text);
		postEntity.setCreationDate(new Date());

		this.postRepository.saveAndFlush(postEntity);
	}

	public void deletePost(long postId) {
		PostEntity postEntity;

		postEntity = this.postRepository.findOne(postId);
		if (postEntity != null) {
			this.commentService.deleteComments(postEntity.getCommentEntities());
			this.postRepository.delete(postEntity);
		}

	}

	public List<PostDto> getPostsForUser(long id) {
		List<Long> ids;

		ids = this.userConnectionService.getFriendsIdByUserCredentialId(id);
		ids.add(id);
		return this.parseEntitiesToDtos(this.postRepository
				.findByAuthorIds(ids));
	}

	private List<PostDto> parseEntitiesToDtos(List<PostEntity> entities) {
		List<PostDto> dtos;
		PostDto dto;

		dtos = new ArrayList<PostDto>();
		for (PostEntity entity : entities) {
			dto = new PostDto();
			dto.setUserProfileDataDto(this.userProfileDataService
					.parseEntityToDto(entity.getUserProfileData()));
			dto.setAuthorCredentialId(entity.getUserProfileData()
					.getCredentialId());
			dto.setPostId(entity.getPostId());
			dto.setText(entity.getText());
			dto.setCreationDate(new SimpleDateFormat("YYYY.MM.dd HH:mm:ss")
					.format(entity.getCreationDate()));
			dto.setCommentDtos(this.commentService.parseEntitiesToDtos(entity
					.getCommentEntities()));
			dtos.add(dto);
		}
		return dtos;
	}
}
