package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Data
public class SearchRequest implements Serializable {

	private static final long serialVersionUID = 6181131764545250397L;
	
	@Size(min=1, max=128) @NotNull @Email
	private String email;
	
	@NotNull @Size(min=1, max=128)
    private String fullName;
	
	@NotNull @Size(min=1, max=128)
    private String place;
	
	@NotNull @Size(min=1, max=128)
    private String job;
}
