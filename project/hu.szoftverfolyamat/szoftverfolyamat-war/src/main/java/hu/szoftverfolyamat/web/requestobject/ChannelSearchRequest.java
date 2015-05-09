package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChannelSearchRequest implements Serializable {

	private static final long serialVersionUID = 6537913715335344597L;
	
	private String searchTerm;

}
