package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchRequest implements Serializable {

	private static final long serialVersionUID = 6181131764545250397L;
	
	private String email;
    private String fullName;
    private String place;
    private String job;
}
