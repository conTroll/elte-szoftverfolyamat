package hu.szoftverfolyamat.web.requestobject;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ChannelSearchRequest implements Serializable {

	private static final long serialVersionUID = 6537913715335344597L;
	
	private String searchTerm;

}
