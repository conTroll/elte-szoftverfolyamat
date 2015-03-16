package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentRequest implements Serializable {

    private Long postId;
    private String text;
}
