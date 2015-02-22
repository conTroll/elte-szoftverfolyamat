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

	public void createNewPost(final String text, final long ownerId) {
		final PostEntity postEntity  = new PostEntity();
		postEntity.setUserProfileData(userProfileDataService.findByUserCredentialId(ownerId));
		postEntity.setText(text);
		postEntity.setCreationDate(new Date());
		postRepository.saveAndFlush(postEntity);
	}

	public void deletePost(final long postId) {
		final PostEntity postEntity = postRepository.findOne(postId);

		if (postEntity != null) {
			commentService.deleteComments(postEntity.getCommentEntities());
			postRepository.delete(postEntity);
		}
	}

	public List<PostDto> getPostsForUser(final long id) {
		List<Long> ids = userConnectionService.getFriendsIdByUserCredentialId(id);
		ids.add(id);
		return parseEntitiesToDtos(postRepository.findByAuthorIds(ids));
	}

	private List<PostDto> parseEntitiesToDtos(final List<PostEntity> entities) {
		final List<PostDto> dtos = new ArrayList<PostDto>();

		for (final PostEntity entity : entities) {
            final PostDto dto = new PostDto();
			dto.setUserProfileDataDto(userProfileDataService.parseEntityToDto(entity.getUserProfileData()));
			dto.setAuthorCredentialId(entity.getUserProfileData().getCredentialId());
			dto.setPostId(entity.getPostId());
			dto.setText(entity.getText());
			dto.setCreationDate(new SimpleDateFormat("YYYY.MM.dd HH:mm:ss").format(entity.getCreationDate()));
			dto.setCommentDtos(commentService.parseEntitiesToDtos(entity.getCommentEntities()));
			dtos.add(dto);
		}

		return dtos;
	}
}
