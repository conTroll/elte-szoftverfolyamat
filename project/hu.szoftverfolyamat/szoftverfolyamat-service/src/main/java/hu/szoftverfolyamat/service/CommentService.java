package hu.szoftverfolyamat.service;

import hu.szoftverfolyamat.entity.CommentEntity;
import hu.szoftverfolyamat.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserProfileDataService userProfileDataService;

	public void createComment(final Long authorCredentialId, final Long postId, final String text) {
		final CommentEntity entity = new CommentEntity();
		entity.setUserProfileData(userProfileDataService.findByUserCredentialId(authorCredentialId));
		entity.setPostId(postId);
		entity.setText(text);
		entity.setCreationDate(new Date());
		commentRepository.saveAndFlush(entity);
	}

	public void deleteCommentById(final Long id) {
		commentRepository.delete(id);
	}

	public void deleteComments(final List<CommentEntity> entities) {
        commentRepository.delete(entities);
	}
}
