package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.dto.PostDto;
import hu.szoftverfolyamat.entity.PostEntity;
import hu.szoftverfolyamat.repository.PostRepository;
import hu.szoftverfolyamat.service.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostMapper postMapper;

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
		final List<Long> ids = userConnectionService.getFriendsIdByUserCredentialId(id);
		ids.add(id);
		return postMapper.apply(postRepository.findByAuthorIds(ids));
	}
}
