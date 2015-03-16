package hu.szoftverfolyamat.service.mapper;

import hu.szoftverfolyamat.dto.CommentDto;
import hu.szoftverfolyamat.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class CommentMapper extends AbstractMapper<CommentEntity, CommentDto> {

    @Autowired
    private UserProfileDataMapper userProfileDataMapper;

    @Override
    public CommentDto apply(final CommentEntity entity) {
        final CommentDto result = new CommentDto();
        result.setUserProfileDataDto(this.userProfileDataMapper.apply(entity.getUserProfileData()));
        result.setCommentId(entity.getCommentId());
        result.setCreationDate(new SimpleDateFormat("YYYY.MM.dd HH:mm:ss").format(entity.getCreationDate()));
        result.setPostId(entity.getPostId());
        result.setText(entity.getText());
        return result;
    }
}
