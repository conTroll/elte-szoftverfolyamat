package hu.szoftverfolyamat.service.mapper;

import hu.szoftverfolyamat.dto.CommentDto;
import hu.szoftverfolyamat.dto.PostDto;
import hu.szoftverfolyamat.entity.CommentEntity;
import hu.szoftverfolyamat.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostMapper extends AbstractMapper<PostEntity, PostDto> {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserProfileDataMapper userProfileDataMapper;

    @Override
    public PostDto apply(final PostEntity entity) {
        final PostDto result = new PostDto();
        result.setUserProfileDataDto(userProfileDataMapper.apply(entity.getUserProfileData()));
        result.setAuthorCredentialId(entity.getUserProfileData().getCredentialId());
        result.setPostId(entity.getPostId());
        result.setText(entity.getText());
        result.setCreationDate(new SimpleDateFormat("YYYY.MM.dd HH:mm:ss").format(entity.getCreationDate()));

        final List<CommentDto> commentDtoList = new ArrayList<CommentDto>();
        for (CommentEntity commentEntity : entity.getCommentEntities()) {
            commentDtoList.add(commentMapper.apply(commentEntity));
        }

        result.setCommentDtos(commentDtoList);
        return result;
    }
}
