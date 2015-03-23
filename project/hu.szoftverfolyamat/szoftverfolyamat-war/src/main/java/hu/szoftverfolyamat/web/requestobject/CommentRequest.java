package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentRequest implements Serializable {

	private static final long serialVersionUID = -1958879080291824988L;
	
	private Long postId;
    private String text;
}
