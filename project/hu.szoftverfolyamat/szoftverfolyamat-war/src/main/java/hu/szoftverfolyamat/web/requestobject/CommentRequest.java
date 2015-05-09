package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CommentRequest implements Serializable {

	private static final long serialVersionUID = -1958879080291824988L;
	
    private Long postId;
    
	@Size(min=1, max=21000) @NotNull	    
    private String text;
}
