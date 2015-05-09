package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class MessageRequest implements Serializable {
	
	private static final long serialVersionUID = 5635216315012866471L;
 
    private Long recipientId;
    
    @Size(min=1, max=21000)
    @NotNull
    private String text;
}
