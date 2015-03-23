package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;


@Data
public class MessageRequest implements Serializable {

	private static final long serialVersionUID = 5635216315012866471L;
	
	private Long recipientId;
    private String text;
}
