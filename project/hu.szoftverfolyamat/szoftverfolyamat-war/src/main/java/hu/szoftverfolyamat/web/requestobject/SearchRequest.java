package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class SearchRequest implements Serializable {

	private static final long serialVersionUID = 6181131764545250397L;
	
	@Size(max=128) @Email
	private String email;
	
	@Size(max=128)
    private String fullName;
	
	@Size(max=128)
    private String place;
	
	@Size(max=128)
    private String job;
}
